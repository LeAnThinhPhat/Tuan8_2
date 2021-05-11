package com.example.tuan8_2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<com.example.tuan8_bai2.User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<com.example.tuan8_bai2.User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uid LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    com.example.tuan8_bai2.User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE uid LIKE :id ")
    com.example.tuan8_bai2.User findById(int id);

    @Insert
    void insertAll(com.example.tuan8_bai2.User... users);

    @Delete
    void delete(com.example.tuan8_bai2.User user);

    @Update(entity = com.example.tuan8_bai2.User.class)
    void update(com.example.tuan8_bai2.User... user);
//    @Update(entity = Playlist.class)
//    public void updateCategory(PlaylistCategory... category);
}
