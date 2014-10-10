package remedy.export;

import java.sql.Connection;

public interface Connectable {

	public Boolean getIsConnected();

	public void setIsConnected(Boolean isConnected);

	public Connection getConn();

	public void setConn(Connection conn);
}
