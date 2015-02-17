public class BitManips {

    // -- -- --
    int rotateLeft(int x, int k) {
        
        // [ //
        return (x << k) | (x >> (32 - k));
        // ] //
    }

    // -- -- --
    int isMultipleOfFour(int x) {

        // [ //
        // Think: return !(x & 0b11);
        return !(x & 3);
        // ] //

    }

    // -- -- --
    int isOdd(int x) {

        // [ //
        return x != ((x >> 1) << 1);
        // ] //
    }

    // -- -- --
    int times35(int x) {

        // [ //
        // 35 = 32 + 2 + 1 = 2^5 + 2^1 + 2^0
        return (x << 5) + (x << 2) + x;
        // ] //
    }
}
