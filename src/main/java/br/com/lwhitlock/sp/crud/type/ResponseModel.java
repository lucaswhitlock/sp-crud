/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lwhitlock
 * @param <T>
 */
public class ResponseModel<T> implements Serializable {

    private List<T> data;

    private String version = "1.00";

    private List<Message> messages;

    public ResponseModel() {
        this.data = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public ResponseModel(List<T> data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseModel<?> other = (ResponseModel<?>) obj;
        return true;
    }

}
