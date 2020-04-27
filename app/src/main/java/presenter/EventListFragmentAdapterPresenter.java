package presenter;

import java.util.List;

import common.Events;
import controller.EventListFragmentAdapterController;


public class EventListFragmentAdapterPresenter {

    List<Events> eventsList;

    public EventListFragmentAdapterPresenter(List<Events> eventsList)
    {
        this.eventsList=eventsList;
    }

    public void allEvents(int position, EventListFragmentAdapterController.View holder)
    {
         Events events=eventsList.get(position);
        {


            if(events!=null)
            {
                holder.setTitle(events.getTitle());
                holder.setDescription(events.getDescription());


                holder.setStartDate(events.getStartDate());

            }
        }
    }

    public int getEventCount() {
        return eventsList.size();
    }

    public Events getEventId(int position) {
        Events events = eventsList.get(position);
        return events;
    }
}
