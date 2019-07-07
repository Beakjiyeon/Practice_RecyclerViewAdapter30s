package com.example.recyclehoney;
// 리사이클어댑터 30초 만에 만들기
// https://www.youtube.com/watch?v=7UgiC4f1cfw
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        //어댑터 만들기
        PersonAdapter adapter=new PersonAdapter(new PersonAdapter.OnPersonClickListener() {
            @Override
            public void onPersonClicked(Person model) {
                Toast.makeText(MainActivity.this, model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        List<Person> people=new ArrayList<>();
        people.add(new Person("홍길동",21));
        people.add(new Person("백지연",22));
        Toast.makeText(this, "사람추가", Toast.LENGTH_SHORT).show();

        adapter.setItems(people);
        Log.d("백지연","dd");
    }
    private static class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
        interface OnPersonClickListener {
            void onPersonClicked(Person model);
        }
        
        private OnPersonClickListener mListener;
        
        private List<Person> mItems = new ArrayList<>();
    
        public PersonAdapter() {}
    
        public PersonAdapter(OnPersonClickListener listener) {
            mListener = listener;
        }
    
        public void setItems(List<Person> items) {
            this.mItems = items;
            notifyDataSetChanged();
        }
    
        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_person, parent, false);
            final PersonViewHolder viewHolder = new PersonViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        final Person item = mItems.get(viewHolder.getAdapterPosition());
                        mListener.onPersonClicked(item);
                    }
                }
            });
            return viewHolder;
        }
    
        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
            Person item = mItems.get(position);
            // TODO : 데이터를 뷰홀더에 표시하시오
            holder.name.setText(item.getName());
            holder.age.setText(item.getAge()+"");
        }
    
        @Override
        public int getItemCount() {
            return mItems.size();
        }
    
        public static class PersonViewHolder extends RecyclerView.ViewHolder {
            // TODO : 뷰홀더 완성하시오
            TextView name;
            TextView age;
            
            public PersonViewHolder(@NonNull View itemView) {
                super(itemView);
                // TODO : 뷰홀더 완성하시오
                name=itemView.findViewById(R.id.name_text);
                age=itemView.findViewById(R.id.age_text);
            }
        }
    }
}
