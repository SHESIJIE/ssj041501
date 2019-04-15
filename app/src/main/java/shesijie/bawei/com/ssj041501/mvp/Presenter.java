package shesijie.bawei.com.ssj041501.mvp;

public class Presenter implements Constan.ILoginPresenter {
    Model model;
   Constan.ILoginView view;
    @Override
    public void getLoginModel(String url , final String name , String pwd) {
         model.login ( url , name , pwd , new Constan.ILoginModel.CallBack () {
             @Override
             public void onSuccess(String names) {
                 view.getLogin ( names );
             }

             @Override
             public void onFail() {

             }
         } );
    }

    @Override
    public void getResigtModel(String url , String name , String pwd) {
        model.resigt ( url , name , pwd , new Constan.ILoginModel.CallBack () {
            @Override
            public void onSuccess(String names) {
                view.getLogin ( names );
            }

            @Override
            public void onFail() {

            }
        } );
    }

    @Override
    public void Attch(Constan.ILoginView iLoginView) {
        this.view = iLoginView;
          model = new Model ();
    }

    @Override
    public void Deach() {
        if (view != null) {
            view = null;
        }
        if (model != null) {
            model = null;
        }
        System.gc ();
    }
}
