package common;

public class EventResponseHandler {
    public interface ResponseListener<T> {
        void onSuccess(T response);
    }

    public interface ErrorListener<T> {
        void onError(Throwable errorWrapper);
    }
}
