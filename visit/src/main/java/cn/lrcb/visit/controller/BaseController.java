package cn.lrcb.visit.controller;
import cn.lrcb.visit.model.BaseModel;
public class BaseController {
	public static final String MSG_SUCC = "请求成功";
    public static final String MSG_ERROR = "请求失败";

    public static final String MSG_ADD_ERROR = "添加失败";
    public static final String MSG_ADD_SUCC = "添加成功";

    public static final String MSG_UPDATE_ERROR = "更新失败";
    public static final String MSG_UPDATE_SUCC = "更新成功";

    public static final String MSG_DELETE_ERROR = "删除失败";
    public static final String MSG_DELETE_SUCC = "删除成功";


    public static final int SUCC_CODE = 0;

    public static final int ERROR_CODE = 1;

    public static final int NO_LOGIN = 101;

    /**
     * @param code
     * @return
     */
    public BaseModel makeModel(int code) {
        BaseModel model = new BaseModel();
        if (code == SUCC_CODE) {
            model.setMsg(MSG_SUCC);
        } else if (code == ERROR_CODE) {
            model.setMsg(MSG_ERROR);
        }
        model.setCode(code);
        return model;
    }

    /**
     * @param code
     * @param msg
     * @return
     */
    public BaseModel makeModel(int code, String msg) {
        BaseModel model = new BaseModel();
        model.setCode(code);
        model.setMsg(msg);
        return model;
    }

    /**
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public BaseModel makeModel(int code, String msg, Object data) {
        BaseModel model = new BaseModel();
        model.setCode(code);
        model.setData(data);
        model.setMsg(msg);
        return model;
    }
}
