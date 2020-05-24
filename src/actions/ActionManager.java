package actions;

public class ActionManager {

    private OpenBasicTableAction openBasicTableAction;
    private AddRowAction addRowAction;
    private RemoveRowAction removeRowAction;
    private RefreshAction refreshAction;
    private FilterAction filterAction;
    private RelationsAction relationsAction;
    private ReportAction reportAction;
    private SearchAction searchAction;

    public ActionManager() {
        openBasicTableAction = new OpenBasicTableAction();
        addRowAction = new AddRowAction();
        removeRowAction = new RemoveRowAction();
        refreshAction = new RefreshAction();
        filterAction = new FilterAction();
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

    public RefreshAction getRefreshAction() {
        return refreshAction;
    }

    public void setRefreshAction(RefreshAction refreshAction) {
        this.refreshAction = refreshAction;
    }

    public FilterAction getFilterAction() {
        return filterAction;
    }

    public void setFilterAction(FilterAction filterAction) {
        this.filterAction = filterAction;
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
