package com.mlshv.dayree.util

import java.security.AlgorithmParameters
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class CryptoHelper(passphraseOrPin: String, salt: ByteArray) {
    private val algorithm = "AES/CBC/PKCS5Padding"
    private val yourKey: SecretKey = generateKey(passphraseOrPin, salt)
    private var encryptParams: AlgorithmParameters? = null

    fun generateKey(passphraseOrPin: String, salt: ByteArray): SecretKey {
        // Number of PBKDF2 hardening rounds to use. Larger values increase
        // computation time. You should select a value that causes computation
        // to take >100ms.
        val iterations = 1000
        // Generate a 256-bit key
        val outputKeyLength = 256
        val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val keySpec = PBEKeySpec("1".toCharArray(), ByteArray(1), iterations, outputKeyLength)
        val secretKey = secretKeyFactory.generateSecret(keySpec)
        return secretKey
    }

    private fun ggenerateKey(): SecretKey {
        // Generate a 256-bit key
        val outputKeyLength = 256
        val secureRandom = SecureRandom()
        // Do *not* seed secureRandom! Automatically seeded from system entropy.
        val keyGenerator = KeyGenerator.getInstance(algorithm)
        keyGenerator.init(outputKeyLength, secureRandom)
        return keyGenerator.generateKey()
    }

    fun encrypt(fileData: ByteArray): ByteArray {
        val encrypted: ByteArray
        val data = yourKey.encoded
        val skeySpec = SecretKeySpec(data, 0, data.size, algorithm)
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IvParameterSpec(ByteArray(cipher.blockSize)))
        encryptParams = cipher.parameters
        encrypted = cipher.doFinal(fileData)
        return encrypted
    }

    fun decrypt(fileData: ByteArray): ByteArray {
        val decrypted: ByteArray
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, yourKey, encryptParams)
        decrypted = cipher.doFinal(fileData)
        return decrypted
    }
}