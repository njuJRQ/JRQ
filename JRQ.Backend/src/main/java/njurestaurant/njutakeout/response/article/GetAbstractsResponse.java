package njurestaurant.njutakeout.response.article;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class GetAbstractsResponse extends Response {
	private List<AbstractItem> abstractList; //首页所有文章列表

	public GetAbstractsResponse(){
	}

	public GetAbstractsResponse(List<AbstractItem> abstractList) {
		this.abstractList = abstractList;
	}

	public List<AbstractItem> getAbstractList() {
		return abstractList;
	}

	public void setAbstractList(List<AbstractItem> abstractList) {
		this.abstractList = abstractList;
	}
}
