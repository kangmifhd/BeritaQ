package wahyouwebid.beritaq.data.model.source

import com.google.gson.annotations.SerializedName

data class ResponseSource(

	@field:SerializedName("sources")
	val sources: List<DataSource>,

	@field:SerializedName("status")
	val status: String
)