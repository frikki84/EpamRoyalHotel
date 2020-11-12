package by.epamtc.jwd2020.dziadkouskaya.bean;

public class SpesialClient {
	private int spesialClientId;
	private String spesialClientName;
	
	public SpesialClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpesialClient(String spesialClientName) {
		super();
		this.spesialClientName = spesialClientName;
	}
	
	public SpesialClient(int spesialClientId, String spesialClientName) {
		super();
		this.spesialClientId = spesialClientId;
		this.spesialClientName = spesialClientName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + spesialClientId;
		result = prime * result + ((spesialClientName == null) ? 0 : spesialClientName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpesialClient other = (SpesialClient) obj;
		if (spesialClientId != other.spesialClientId)
			return false;
		if (spesialClientName == null) {
			if (other.spesialClientName != null)
				return false;
		} else if (!spesialClientName.equals(other.spesialClientName))
			return false;
		return true;
	}
	public int getSpesialClientId() {
		return spesialClientId;
	}
	public void setSpesialClientId(int spesialClientId) {
		this.spesialClientId = spesialClientId;
	}
	public String getSpesialClientName() {
		return spesialClientName;
	}
	public void setSpesialClientName(String spesialClientName) {
		this.spesialClientName = spesialClientName;
	}
	@Override
	public String toString() {
		return getClass() + " [spesialClientId=" + spesialClientId + ", spesialClientName=" + spesialClientName + "]";
	}
	
	

}
