package com.wealthdoctor.bill_reminder.expandable_recycler_view_adapter;


import com.wealthdoctor.R;

import java.util.Arrays;
import java.util.List;

public class ReminderDataFactory {

    public static List<ParentProvider> makeGenres() {
        return Arrays.asList(makeRockGenre(),
                makeJazzGenre(),
                makeClassicGenre(),
                makeSalsaGenre(),
                makeBluegrassGenre());
    }


    public static ParentProvider makeRockGenre() {
        return new ParentProvider("Airtel", makeRockArtists(), R.mipmap.ic_launcher_round);
    }


    public static List<ChildProvider> makeRockArtists() {
        ChildProvider airtel = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);
        return Arrays.asList(airtel);
    }

    public static ParentProvider makeJazzGenre() {
        return new ParentProvider("SBI", makeJazzArtists(), R.mipmap.ic_launcher_round);
    }


    public static List<ChildProvider> makeJazzArtists() {
        ChildProvider SBI = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);

        return Arrays.asList(SBI);
    }

    public static ParentProvider makeClassicGenre() {
        return new ParentProvider("Axis Bank", makeClassicArtists(), R.mipmap.ic_launcher_round);
    }


    public static List<ChildProvider> makeClassicArtists() {
        ChildProvider AxisBank = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);
        return Arrays.asList(AxisBank);
    }

    public static ParentProvider makeSalsaGenre() {
        return new ParentProvider("BMTC", makeSalsaArtists(), R.mipmap.ic_launcher_round);
    }


    public static List<ChildProvider> makeSalsaArtists() {
        ChildProvider BMTC = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);
        return Arrays.asList(BMTC);
    }

    public static ParentProvider makeBluegrassGenre() {
        return new ParentProvider("BSNL", makeBluegrassArtists(), R.mipmap.ic_launcher_round);
    }


    public static List<ChildProvider> makeBluegrassArtists() {
        ChildProvider BSNL = new ChildProvider("Already Paid", R.id.child_delete, R.id.child_edit, true);
        return Arrays.asList(BSNL);
    }

}

