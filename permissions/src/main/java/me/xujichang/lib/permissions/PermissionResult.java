package me.xujichang.lib.permissions;

import java.util.List;

/**
 * Info:for LiveDataPermissions  in me.xujichang.lib.permissions.PermissionResult
 * Des:
 *
 * @author xujichang
 * @version 1.0.0
 * @date 2020/2/26 11:10 PM
 * @since 1.0.0
 */
public class PermissionResult {
    private List<String> mList;
    private Type mType;

    public PermissionResult(List<String> pList) {
        mList = pList;
    }

    public PermissionResult(List<String> pList, Type pType) {
        mList = pList;
        mType = pType;
    }

    public enum Type {
        ACCEPT, DENIED, RATIONAL
    }

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> pList) {
        mList = pList;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type pType) {
        mType = pType;
    }
}
