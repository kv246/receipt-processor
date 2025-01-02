package org.openapitools.model;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ReceiptsProcessPost200Response
 */

@JsonTypeName("_receipts_process_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-30T19:40:12.559624-06:00[America/Chicago]", comments = "Generator version: 7.10.0")
public class ReceiptsProcessPost200Response {

  private String id;

  public ReceiptsProcessPost200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ReceiptsProcessPost200Response(String id) {
    this.id = id;
  }

  public ReceiptsProcessPost200Response id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Pattern(regexp = "^\\S+$") 
  @Schema(name = "id", example = "adb6b560-0eef-42bc-9d16-df48f30e89b2", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReceiptsProcessPost200Response receiptsProcessPost200Response = (ReceiptsProcessPost200Response) o;
    return Objects.equals(this.id, receiptsProcessPost200Response.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReceiptsProcessPost200Response {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

