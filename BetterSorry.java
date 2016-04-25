class BetterSorry implements State {
    private byte[] value;
    private byte maxval;
    private Object iLock = new Object();
    private Object jLock = new Object();
    BetterSorry(byte[] v) { value = v; maxval = 127; }

    BetterSorry(byte[] v, byte m) { value = v; maxval = m; }

    public int size() { return value.length; }

    public byte[] current() { return value; }

    public boolean swap(int i, int j) {
		if (value[i] <= 0 || value[j] >= maxval) {
	    	return false;
		}
		synchronized(iLock){
			value[i]--;
		}
		synchronized(jLock){
			value[j]++;	
		}
		
		return true;
    }
}
