package com.example.secondhand_platform.dao.impl;

import com.example.secondhand_platform.dao.GoodsDAO;
import com.example.secondhand_platform.model.Goods;
import com.example.secondhand_platform.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAOimpl implements GoodsDAO {
    @Override
    public int addGoods(Goods goods) {
        String sql = "INSERT INTO goods(user_id, goods_name, goods_type, price, description, status) VALUES (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goods.getUserId());
            ps.setString(2, goods.getGoodsName());
            ps.setString(3, goods.getGoodsType());
            ps.setBigDecimal(4, goods.getPrice());
            ps.setString(5, goods.getDescription());
            ps.setInt(6, goods.getStatus());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return 0;
    }

    @Override
    public int updateGoods(Goods goods) {
        String sql = "UPDATE goods SET goods_name=?, goods_type=?, price=?, description=?, status=? WHERE id=? AND user_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsType());
            ps.setBigDecimal(3, goods.getPrice());
            ps.setString(4, goods.getDescription());
            ps.setInt(5, goods.getStatus());
            ps.setInt(6, goods.getId());
            ps.setInt(7, goods.getUserId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return 0;
    }

    @Override
    public int deleteGoods(Integer id, Integer userId) {
        String sql = "DELETE FROM goods WHERE id=? AND user_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps);
        }
        return 0;
    }

    @Override
    public List<Goods> searchGoods(String keyword) {
        String sql = "SELECT * FROM goods WHERE goods_name LIKE ? AND status=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<>();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setUserId(rs.getInt("user_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsType(rs.getString("goods_type"));
                goods.setPrice(rs.getBigDecimal("price"));
                goods.setDescription(rs.getString("description"));
                goods.setStatus(rs.getInt("status"));
                goods.setCreateTime(rs.getDate("create_time"));
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return goodsList;
    }

    @Override
    public Goods findById(Integer id) {
        String sql = "SELECT * FROM goods WHERE id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setUserId(rs.getInt("user_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsType(rs.getString("goods_type"));
                goods.setPrice(rs.getBigDecimal("price"));
                goods.setDescription(rs.getString("description"));
                goods.setStatus(rs.getInt("status"));
                goods.setCreateTime(rs.getDate("create_time"));
                return goods;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<Goods> findByUserId(Integer userId) {
        String sql = "SELECT * FROM goods WHERE user_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<>();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setUserId(rs.getInt("user_id"));
                goods.setGoodsName(rs.getString("goods_name"));
                goods.setGoodsType(rs.getString("goods_type"));
                goods.setPrice(rs.getBigDecimal("price"));
                goods.setDescription(rs.getString("description"));
                goods.setStatus(rs.getInt("status"));
                goods.setCreateTime(rs.getDate("create_time"));
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return goodsList;
    }
}
