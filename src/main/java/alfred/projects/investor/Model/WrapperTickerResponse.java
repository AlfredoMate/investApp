package alfred.projects.investor.Model;

import java.util.List;

public class WrapperTickerResponse {

    private MetaDTO meta;
    private List<Ticker> data;

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    public List<Ticker> getData() {
        return data;
    }

    public void setData(List<Ticker> data) {
        this.data = data;
    }
}
