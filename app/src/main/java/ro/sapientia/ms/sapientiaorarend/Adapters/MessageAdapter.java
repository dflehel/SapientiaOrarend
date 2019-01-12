package ro.sapientia.ms.sapientiaorarend.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ro.sapientia.ms.sapientiaorarend.R;
import ro.sapientia.ms.sapientiaorarend.models.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {


    private ArrayList<Message> messages = new ArrayList<>();


    public MessageAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public MessageAdapter() {
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item, viewGroup, false);
        return new MessageAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder messageAdapterViewHolder, int i) {
        Date date = new Date(this.messages.get(i).getTimestamp());
        messageAdapterViewHolder.temistamp.setText(date.toString());
        messageAdapterViewHolder.sender.setText(this.messages.get(i).getSender());
        messageAdapterViewHolder.content.setText(this.messages.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView temistamp;
        public TextView sender;
        public TextView content;


        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.temistamp = (TextView) itemView.findViewById(R.id.message_item_time_stemp_text_view);
            this.sender = (TextView) itemView.findViewById(R.id.message_ite_send_text_view);
            this.content = (TextView) itemView.findViewById(R.id.message_item_content_text_view);
        }
    }
}
