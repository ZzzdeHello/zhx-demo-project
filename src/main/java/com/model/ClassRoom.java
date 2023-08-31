package com.model;

public class ClassRoom {
    private int roomNum;
    private String roomName;

    public ClassRoom() {
    }

    public ClassRoom(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
