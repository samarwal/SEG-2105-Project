package com.example.group.ondemandhomerepair;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UnitTestThree {

    @Test
    public void canAddService(){

        Service user = new Service(
                "Test_2",
                120
        );

        FirebaseDatabase.getInstance().getReference("Services")
                .push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                assertTrue(task.isSuccessful());
            }
        });
    }
}
