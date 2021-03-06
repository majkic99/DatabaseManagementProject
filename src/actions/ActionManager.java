package actions;

public class ActionManager {

    private OpenBasicTableAction openBasicTableAction;
    private AddRowAction addRowAction;
    private RemoveRowAction removeRowAction;
    private EditAction editAction;
    private FilterAndSortAction filterAndSortAction;
    private RelationsAction relationsAction;
    private ReportAction reportAction;
    private SearchAction searchAction;

    public ActionManager() {
        openBasicTableAction = new OpenBasicTableAction();
        addRowAction = new AddRowAction();
        removeRowAction = new RemoveRowAction();
        editAction = new EditAction();
        filterAndSortAction = new FilterAndSortAction();
        relationsAction = new RelationsAction();
        reportAction = new ReportAction();
        searchAction = new SearchAction();
    }

    public AddRowAction getAddRowAction() {
        return addRowAction;
    }

    public void setAddRowAction(AddRowAction addRowAction) {
        this.addRowAction = addRowAction;
    }

    public RemoveRowAction getRemoveRowAction() {
        return removeRowAction;
    }

    public void setRemoveRowAction(RemoveRowAction removeRowAction) {
        this.removeRowAction = removeRowAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }

    public void setEditAction(EditAction editAction) {
        this.editAction = editAction;
    }

    public FilterAndSortAction getFilterAndSortAction() {
        return filterAndSortAction;
    }

    public void setFilterAndSortAction(FilterAndSortAction filterAndSortAction) {
        this.filterAndSortAction = filterAndSortAction;
    }

    public RelationsAction getRelationsAction() {
        return relationsAction;
    }

    public void setRelationsAction(RelationsAction relationsAction) {
        this.relationsAction = relationsAction;
    }

    public ReportAction getReportAction() {
        return reportAction;
    }

    public void setReportAction(ReportAction reportAction) {
        this.reportAction = reportAction;
    }

    public SearchAction getSearchAction() {
        return searchAction;
    }

    public void setSearchAction(SearchAction searchAction) {
        this.searchAction = searchAction;
    }

    public OpenBasicTableAction getOpenBasicTableAction() {
        return openBasicTableAction;
    }

    public void setOpenBasicTableAction(OpenBasicTableAction openBasicTableAction) {
        this.openBasicTableAction = openBasicTableAction;
    }
}
