package com.aptos.utils;

import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;

/**
 * @author liqiang
 */
public class Signature {

    public static byte[] ed25519Sign(byte[] privateKey, byte[] data) {
        Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = new Ed25519PrivateKeyParameters(privateKey,
                0);
        Ed25519Signer ed25519Signer = new Ed25519Signer();
        ed25519Signer.init(true, ed25519PrivateKeyParameters);
        ed25519Signer.update(data, 0, data.length);
        byte[] rst = ed25519Signer.generateSignature();

        return rst;
    }

    public static boolean ed25519Verify(byte[] publicKey, byte[] signingMessage, byte[] signature) {
        Ed25519PublicKeyParameters ed25519PublicKeyParameters = new Ed25519PublicKeyParameters(publicKey, 0);
        Ed25519Signer ed25519Signer = new Ed25519Signer();
        ed25519Signer.init(false, ed25519PublicKeyParameters);
        ed25519Signer.update(signingMessage, 0, signingMessage.length);

        return ed25519Signer.verifySignature(signature);
    }

    public static String getPublicKey(byte[] privateKey) {
        Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = new Ed25519PrivateKeyParameters(privateKey,
                0);

        return Hex.encode(ed25519PrivateKeyParameters.generatePublicKey().getEncoded());
    }

}