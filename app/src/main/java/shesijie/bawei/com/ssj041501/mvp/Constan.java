package shesijie.bawei.com.ssj041501.mvp;

public interface Constan {
    public interface ILoginView{
        void getLogin(String data);

    }
    public interface IResigtView{
        void getResigt(String data);
    }
    public interface ILoginModel{
        void  login(String urls, String username, String pswd,ILoginModel.CallBack callBack);

        interface CallBack{
            //成功返回
            void  onSuccess(String names);
            //失败返回
            void  onFail();
        }
    }
    public interface IResigtModel{
        void  resigt(String urls, String username, String pswd,ILoginModel.CallBack callBack);

        interface CallBack{
            //成功返回
            void  onSuccess(String names);
            //失败返回
            void  onFail();
        }
    }
    public interface ILoginPresenter{
        void getLoginModel(String url,String name,String pass);
        void getResigtModel(String url,String name,String pass);
        void Attch(ILoginView iLoginView);
        void Deach();
    }

}
