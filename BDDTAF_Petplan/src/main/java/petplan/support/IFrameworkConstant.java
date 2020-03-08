package petplan.support;

import petplan.reports.ConfigFileReadWrite;

public interface IFrameworkConstant {

	String FILE_FRAMEWORK = "properties/framework.properties";
	String LOCATION_DATATABLE_EXCEL = ConfigFileReadWrite.read(FILE_FRAMEWORK, "LOCATION_DATATABLE_EXCEL");
}
