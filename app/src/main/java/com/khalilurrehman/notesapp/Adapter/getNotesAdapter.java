package com.khalilurrehman.notesapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khalilurrehman.notesapp.Models.NotesData;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mandeep on 4/9/17.
 */

public class getNotesAdapter /*extends RecyclerView.Adapter<getNotesAdapter.MyViewHolder> *//*implements Filterable*/ {
    private Context mContext;
    private List<NotesData> noticeLists;
    private List<NotesData> studentListFiltered;
   // private EvidenceAdapterListener listener;
    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView teacherName,
                notice_type,
                homeworkTitle,
                homeworkDateTv,
                homeworkDesc,
                homeworkCreatedDate,
                circleText,
                homeworkMonth;
        LinearLayout frame;

        public MyViewHolder(View view) {
            super(view);
         /*   teacherName = view.findViewById(R.id.teacherName);
            homeworkTitle = view.findViewById(R.id.homeworkTitle);
            homeworkDateTv = view.findViewById(R.id.homeworkDateTv);
            homeworkDesc = view.findViewById(R.id.homeworkDesc);
            homeworkCreatedDate = view.findViewById(R.id.homeworkCreatedDate);
            homeworkMonth = view.findViewById(R.id.homeworkMonth);
            notice_type = view.findViewById(R.id.notice_type);
            frame= view.findViewById(R.id.frame);
            circleText =view.findViewById(R.id.circleText);*/
        }
    }


 /*   public getNotesAdapter(Context mContext, List<NoticeList> noticeLists, EvidenceAdapterListener listener) {
        this.mContext = mContext;
        this.noticeLists = noticeLists;
        this.studentListFiltered = noticeLists;
        this.listener = listener;
    }*/

   /* @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NoticeList homeworkData = studentListFiltered.get(position);

        String homework_title = homeworkData.getNoticeTitle();

        if (!homework_title.equals("")){
            holder.homeworkTitle.setText(homework_title);
        }
        holder.teacherName.setText(homeworkData.getTEACHERNAME());
        if (!homeworkData.getCreatedOn().equals("")){
            holder.homeworkCreatedDate.setText(new DateConverter().convertDate1(homeworkData.getCreatedOn()));
        }
        if (!homeworkData.getTEACHERIMAGE().equals("")){
            Glide.with(mContext).load(Utils.webUrlHome+homeworkData.getTEACHERIMAGE()).into(holder.teacherImage);
        }
        if (!homeworkData.getNoticeText().equals("")|| homeworkData.getNoticeText()!="null" || homeworkData.getNoticeText()!=NULL){
            String s1= "Notice type: "+homeworkData.getNoticeText();
            SpannableString notice=  new SpannableString(s1);
            notice.setSpan(new RelativeSizeSpan(1.1f), 0,12, 0); // set size
            notice.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 12, 0);// set color

            holder.notice_type.setText(notice);
        }else {
            holder.notice_type.setText("");

        }if (!homeworkData.getNoticeDescription().equals("")){
            String s1= "Description: "+homeworkData.getNoticeDescription();
            SpannableString marks=  new SpannableString(s1);
            marks.setSpan(new RelativeSizeSpan(1.1f), 0,12, 0); // set size
            marks.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 12, 0);// set color

            holder.homeworkDesc.setText(marks);
        }
        holder.homeworkDateTv.setText(new DateConverter().convertDateOnlyDate(homeworkData.getNoticeDate()));
        holder.homeworkMonth.setText(new DateConverter().convertDateOnlyMonth(homeworkData.getNoticeDate()));


    }

    @Override
    public int getItemCount() {
        return studentListFiltered.size();
    }

    public NoticeList getItem(int position){
        return noticeLists.get(position);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    studentListFiltered = noticeLists;
                } else {
                    ArrayList<NoticeList> filteredList = new ArrayList<>();
                    for (NoticeList row : noticeLists) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNoticeTitle().toLowerCase().contains(charString.toLowerCase()) | row.getTEACHERNAME().toLowerCase().contains(charString.toLowerCase()) | row.getNoticeTitle().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    studentListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = studentListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                studentListFiltered = (ArrayList<NoticeList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface EvidenceAdapterListener {
        void onEvidenceSelected(NoticeList getStudentData);
    }*/

}
