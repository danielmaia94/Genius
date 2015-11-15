package Model;

public enum Speed {
	SLOW, MEDIUM, FAST;

	public Speed next() {
		Speed[] v = Speed.values(); 
		if (this.ordinal() < Speed.values().length - 1) {
			return v[this.ordinal() + 1];
		} else {
			return v[0];
		}
	}
}