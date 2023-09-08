package com.delivery.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Application constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

	public static final String REQUEST_PROCESSING_FAILED = "Request Processing Failed";
	public static final String BEARER = "Bearer ";
	public static final String COMMA = ",";
	/*
	 * Application Profiles
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ApplicationProfiles {
		public static final String SPRING_PROFILE_DEFAULT = "default";
		public static final String SPRING_PROFILE_LOCAL = "local";
		public static final String SPRING_PROFILE_DEV = "dev";
		public static final String SPRING_PROFILE_PROD = "prod";
	}

	/*
	 * Request tracking with Correlation Headers
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class RequestTracking {
		public static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-ID";
		public static final String CORRELATION_ID_VAR_NAME = "correlationId";
	}

	/*
	 * Application Config Constants
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ApplicationConfig {
		public static final String APPLICATION_BASE_PACKAGE = "com.broadcom.bip";
		public static final String INTERNAL_EMAIL_DOMAIN = "@broadcom.com";
		public static final String INTERNAL_EMAIL_DOMAIN_NET = "@broadcom.net";

		public static final String[] WHITELISTED_APPLICATION_PATHS = {
				"/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/swagger-ui.html",
				"/swagger-ui/",
				"/webjars/**",
				"/actuator",
				"/error",
				"favicon.ico"
		};
	}

	/*
	 * Exception handling Constants
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ExceptionHandlingConstants {
		public static final String APP_ERR_CODE_501 = "CrushFTPUserAuth501";
		public static final String ERR_AUTHUSER_FILTER = "Something went wrong during authentication service invocation.";
	}

	/*
	 * Pagination and sorting related header variables and default values
	 */
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ServerSidePaginationConstants {
		public static final Integer PAGINATION_DEFAULT_PAGE_NUMBER = 0;
		public static final Integer PAGINATION_DEFAULT_PAGE_SIZE = 50; // Integer.MAX_VALUE
		public static final String PAGINATION_HEADER_X_PAGE_NUMBER = "X-Page-Number";
		public static final String PAGINATION_HEADER_X_PAGE_SIZE = "X-Page-Size";
		public static final String PAGINATION_HEADER_X_SORT_BY = "X-Page-Sort";
		public static final String PAGINATION_HEADER_X_TOTAL_COUNT = "X-Total-Count";
		public static final String PAGINATION_HEADER_LINK = "X-Page-Link";
		public static final String PAGINATION_HEADER_LINK_FORMAT = "<{0}>; rel=\"{1}\"";
		public static final String PAGINATION_HEADER_LINK_TYPE_NEXT = "next";
		public static final String PAGINATION_HEADER_LINK_TYPE_PREV = "prev";
		public static final String PAGINATION_HEADER_LINK_TYPE_FIRST = "first";
		public static final String PAGINATION_HEADER_LINK_TYPE_LAST = "last";
		public static final String PAGINATION_URI_PAGE_NUMBER_PARAM = "page";
		public static final String PAGINATION_URI_PAGE_SIZE_PARAM = "size";
	}

	public static final String[] TRUE_YES_1 = new String[]{"true", "t", "yes", "y", "1"};
	public static final String[] FALSE_NO_0 = new String[]{"false", "f", "no", "n", "0"};
}
