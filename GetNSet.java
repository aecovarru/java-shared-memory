import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSet implements State {
    private AtomicIntegerArray value;
    private byte maxval;

    static AtomicIntegerArray byteToAtomic(byte[] v) {
        int [] arr = new int[v.length];
        for (int i = 0; i < v.length; i++){
            arr[i] = v[i];
        }
        return new AtomicIntegerArray(arr);
    }

    static byte[] atomicToByte(AtomicIntegerArray arr) {
        byte [] bytes = new byte[arr.length()];
        for (int i = 0; i < arr.length(); i++){
            bytes[i] = (byte)arr.get(i);
        }


        return bytes;
    }

    GetNSet(byte[] v) { value = byteToAtomic(v); maxval = 127; }

    GetNSet(byte[] v, byte m) { value = byteToAtomic(v); maxval = m; }

    public int size() { return value.length(); }

    public byte[] current() { return atomicToByte(value); }

    public boolean swap(int i, int j) {
        if (value.get(i) <= 0 || value.get(j) >= maxval) {
            return false;
        }
        value.set(i, value.get(i) - 1);
        value.set(j, value.get(j) + 1);
        return true;
    }
}
