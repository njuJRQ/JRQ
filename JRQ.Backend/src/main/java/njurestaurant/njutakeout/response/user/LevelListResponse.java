package njurestaurant.njutakeout.response.user;

import njurestaurant.njutakeout.response.Response;

import java.util.List;

public class LevelListResponse extends Response{
	private List<LevelItem> levels;

	public LevelListResponse() {
	}

	public LevelListResponse(List<LevelItem> levels) {
		this.levels = levels;
	}

	public List<LevelItem> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelItem> levels) {
		this.levels = levels;
	}
}
