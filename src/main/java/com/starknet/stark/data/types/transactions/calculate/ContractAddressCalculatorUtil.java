package com.starknet.stark.data.types.transactions.calculate;

import com.starknet.stark.crypto.KeccakUtil;
import com.starknet.stark.crypto.StarknetCurve;
import com.starknet.stark.data.types.Felt;
import com.starknet.stark.util.StringUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

public class ContractAddressCalculatorUtil {
    private static Felt CONTRACT_ADDRESS_PREFIX = Felt.fromHex("0x535441524b4e45545f434f4e54524143545f41444452455353");

    public static Felt calculateAddressFromHash(Felt classHash, List<Felt> calldata, Felt salt) {
        return calculateAddressFromHash(classHash, calldata, salt, Felt.ZERO);
    }

    public static Felt calculateAddressFromHash(Felt classHash, List<Felt> calldata, Felt salt, Felt deployerAddress) {
        return StarknetCurve.pedersenOnElements(CONTRACT_ADDRESS_PREFIX, deployerAddress, salt, classHash, StarknetCurve.pedersenOnElements(calldata)
        );
    }
    public Boolean isChecksumAddressValid(String address) {
        return calculateChecksumAddress(Felt.fromHex(address)) == address;
    }
    public static String calculateChecksumAddress(Felt address) {
        String stringAddress = StringUtil.padStart(address.getValue().toString(16).toLowerCase(Locale.ENGLISH),64,'0');
        char[] chars = stringAddress.toCharArray();
        BigInteger hashed = KeccakUtil.keccak(new BigInteger(stringAddress, 16).toByteArray());
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i]) && hashed.testBit(256 - 4 * i - 1)) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return "0x" + new String(chars);
    }
}
