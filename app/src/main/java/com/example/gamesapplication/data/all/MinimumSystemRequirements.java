package com.example.gamesapplication.data.all;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MinimumSystemRequirements {

    @SerializedName("os")
    @Expose
    public String os;
    @SerializedName("processor")
    @Expose
    public String processor;
    @SerializedName("memory")
    @Expose
    public String memory;
    @SerializedName("graphics")
    @Expose
    public String graphics;
    @SerializedName("storage")
    @Expose
    public String storage;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}