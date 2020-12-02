package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class ClientCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int clientCategoryId;
	private String clientCategoryName;
	
	public ClientCategory() {

	}

	public ClientCategory(int clientCategoryId) {
		
		this.clientCategoryId = clientCategoryId;
	}

	public ClientCategory(String clientCategoryName) {
		
		this.clientCategoryName = clientCategoryName;
	}

	public ClientCategory(int clientCategoryId, String clientCategoryName) {
		
		this.clientCategoryId = clientCategoryId;
		this.clientCategoryName = clientCategoryName;
	}

	public int getClientCategoryId() {
		return clientCategoryId;
	}

	public void setClientCategoryId(int clientCategoryId) {
		this.clientCategoryId = clientCategoryId;
	}

	public String getClientCategoryName() {
		return clientCategoryName;
	}

	public void setClientCategoryName(String clientCategoryName) {
		this.clientCategoryName = clientCategoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientCategoryId;
		result = prime * result + ((clientCategoryName == null) ? 0 : clientCategoryName.hashCode());
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
		ClientCategory other = (ClientCategory) obj;
		if (clientCategoryId != other.clientCategoryId)
			return false;
		if (clientCategoryName == null) {
			if (other.clientCategoryName != null)
				return false;
		} else if (!clientCategoryName.equals(other.clientCategoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientCategory [clientCategoryId=" + clientCategoryId + ", clientCategoryName=" + clientCategoryName
				+ "]";
	}

	
	
	
	

}
