package com.base.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {

    private PreparedStatement pstmt;
    private Connection conn;
    private ResultSet rs;
    
 
    /**
     * 打开数据库
     */
    public DBManager() {
        conn = DBConnection.getDBConnection();
    }
    
    
    /**
     * 执行修改添加操作
     * @param coulmn
     * @param type
     * @param sql
     * @return
     * @throws SQLException
     */
    public  boolean deleteAll(String[] coulmn, int[] type, String sql) throws SQLException
    {
        if(!setPstmtParam(coulmn, type, sql))
            return false;
        boolean flag = pstmt.executeUpdate()>0?true:false;
        closeDB();
        return flag;
    }
    
    /**
     * 执行修改添加操作
     * @param coulmn
     * @param type
     * @param sql
     * @return
     * @throws SQLException
     */
    public  boolean updateOrAdd(String[] coulmn, int[] type, String sql) throws SQLException
    {
        if(!setPstmtParam(coulmn, type, sql))
            return false;
        boolean flag = pstmt.executeUpdate()>0?true:false;
        closeDB();
        return flag;
    }
    /**
     * 获取查询结果集
     * @param coulmn
     * @param type
     * @param sql
     * @throws SQLException
     */
    public DataTable getResultData(String[] coulmn, int[] type, String sql) throws SQLException
    {
        DataTable dt = new DataTable();
        
        ArrayList<HashMap<String, String>>list = new ArrayList<HashMap<String, String>>();
        
        if(!setPstmtParam(coulmn, type, sql))
            return null;
        rs = pstmt.executeQuery();
        
        ResultSetMetaData rsmd = rs.getMetaData();//取数据库的列名 
        int numberOfColumns = rsmd.getColumnCount();
        try {
        	  while(rs.next()==true)
              {
                  HashMap<String, String> rsTree = new HashMap<String, String>(); 
                  for(int r=1;r<numberOfColumns+1;r++)
                   {
                     rsTree.put(rsmd.getColumnName(r),String.valueOf(rs.getObject(r)));
                   }
                  list.add(rsTree);
              }
              closeDB();
              if (list.size()>0) {
                  dt.setDataTable(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.toString()+sql+"\n");
		}
      
        return dt;
    }
    
    /**
     * 参数设置
     * @param coulmn
     * @param type
     * @throws SQLException 
     * @throws NumberFormatException 
     */
    private boolean setPstmtParam(String[] coulmn, int[] type, String sql) throws NumberFormatException, SQLException
    {
        if(sql== null) return false;
        pstmt = conn.prepareStatement(sql);
        if(coulmn != null && type != null && coulmn.length !=0 && type.length !=0   )
        {        
            for (int i = 0; i<type.length; i++) {
                switch (type[i]) {
                case Types.INTEGER:
                    pstmt.setInt(i+1, Integer.parseInt(coulmn[i]));
                    break;
                case Types.BOOLEAN:
                    pstmt.setBoolean(i+1, Boolean.parseBoolean(coulmn[i]));
                    break;
                case Types.CHAR:
                    pstmt.setString(i+1, coulmn[i]);
                    break;
                case Types.DOUBLE:
                    pstmt.setDouble(i+1, Double.parseDouble(coulmn[i]));
                    break;
                case Types.FLOAT:
                    pstmt.setFloat(i+1, Float.parseFloat(coulmn[i]));
                    break;
                default:
                    break;
                }
            }
        }
        return true;
    }
    
    /**
     * 关闭数据库
     * @throws SQLException
     */
    private void closeDB() throws SQLException
    {
        if(rs != null)
        {
            rs.close();
        }
        if(pstmt != null)
        {
            pstmt.close();
        }
        if(conn != null)
        {
            conn.close();
        }
        
    }

}
