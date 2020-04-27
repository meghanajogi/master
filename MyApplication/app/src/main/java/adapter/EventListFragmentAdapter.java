package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import common.Events;
import controller.EventListFragmentAdapterController;
import presenter.EventListFragmentAdapterPresenter;

public class EventListFragmentAdapter extends RecyclerView.Adapter<EventListFragmentAdapter.ViewHolder> {


    Context context;
    EventListFragmentAdapterPresenter adapterPresenter;

    private DataListener datalistener;

    public EventListFragmentAdapter(EventListFragmentAdapterPresenter adapterPresenter, Context context, DataListener datalistener) {
        this.context = context;
        this.adapterPresenter = adapterPresenter;
        this.datalistener = datalistener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_item, parent, false), adapterPresenter);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        adapterPresenter.allEvents(position, holder);

    }

    @Override
    public int getItemCount() {
        return adapterPresenter.getEventCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements EventListFragmentAdapterController.View {


        @BindView(R.id.event_title)
        TextView eventTitle;
        @BindView(R.id.description_view)
        TextView descriptionView;
        @BindView(R.id.start_date)
        TextView startDate;
        @BindView(R.id.edit_layout)
        LinearLayout editLayout;
        @BindView(R.id.delete_layout)
        LinearLayout deleteLayout;


        Unbinder unbinder;
        EventListFragmentAdapterPresenter adapterPresenter;

        public ViewHolder(@NonNull View itemView, final EventListFragmentAdapterPresenter adapterPresenter) {
            super(itemView);
            this.adapterPresenter = adapterPresenter;
            unbinder = ButterKnife.bind(this, itemView);

            editLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    datalistener.editOperation(adapterPresenter.getEventId(getAdapterPosition()));


                }
            });

            deleteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AlertDialog.Builder alert = new AlertDialog.Builder(context);

                    alert.setTitle("Alert!!");
                    alert.setMessage("Are you sure to delete the event");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            datalistener.deleteOperation(adapterPresenter.getEventId(getAdapterPosition()));


                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();



                }
            });

        }


        @Override
        public void setTitle(String title) {

            eventTitle.setText(title);

        }

        @Override
        public void setDescription(String description) {
            descriptionView.setText(description);

        }

        @Override
        public void setStartDate(String sdate) {
            startDate.setText(sdate);

        }
    }


    public interface DataListener {

        void editOperation(Events eventId);

        void deleteOperation(Events eventId);


    }
}
