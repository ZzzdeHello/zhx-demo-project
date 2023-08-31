package pattern.builderPattern.interfacePattern;

//ComputerConfigBuilder 构建电脑所用接口
public interface Builder {
    void setCPU();
    void setMemery();
    void setHardDisk();
    void setKeyboard();
    void setMouse();
    Computer getComputer();
}
