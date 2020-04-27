package presenter;

public abstract class BasePresenter<T> {

    private T view;
    private  T dummyView;

    public BasePresenter() {
        dummyView = createDummyView();
    }

    protected abstract T createDummyView();

    public void onAttachView(T view){
        this.view = view;
    }

    public void onDetachView(){
        this.view = null;
    }

    public T getView(){
        if (view == null){

            return dummyView;

        }else {
            return view;
        }
    }
}
