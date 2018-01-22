package com.wealthdoctor.bill_reminder.expandable_recycler_view.listeners;



public interface ExpandCollapseListener {

  /**
   * Called when a group is expanded
   *
   * @param positionStart the flat position of the first child in the {@link com.example.imc_tech.expandablerecyclerview.expandable_recycler_view.models.ExpandableGroup}
   * @param itemCount the total number of children in the {@link com.example.imc_tech.expandablerecyclerview.expandable_recycler_view.models.ExpandableGroup}
   */
  void onGroupExpanded(int positionStart, int itemCount);

  /**
   * Called when a group is collapsed
   *
   * @param positionStart the flat position of the first child in the {@link com.example.imc_tech.expandablerecyclerview.expandable_recycler_view.models.ExpandableGroup}
   * @param itemCount the total number of children in the {@link com.example.imc_tech.expandablerecyclerview.expandable_recycler_view.models.ExpandableGroup}
   */
  void onGroupCollapsed(int positionStart, int itemCount);
}
