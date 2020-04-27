package controller;

import java.util.List;

import common.Events;

public class EventListFragmentController {

    public interface View{
        public void setUpRecyclerView(List<Events> eventsList);
    }


}
