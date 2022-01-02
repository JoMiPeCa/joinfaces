/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.List;

import jakarta.faces.application.ProjectStage;
import jakarta.faces.application.ResourceHandler;
import jakarta.faces.application.StateManager;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.UIInput;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.context.PartialViewContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.validator.BeanValidator;
import jakarta.faces.view.facelets.ResourceResolver;
import jakarta.faces.view.facelets.TagDecorator;
import jakarta.faces.webapp.FacesServlet;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.0.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=314">JSR-314</a>
 * @see <a href="https://stackoverflow.com/a/17341945/3574494">https://stackoverflow.com/a/17341945/3574494</a>
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.jsf")
public class JavaxFaces2_0Properties implements ServletContextInitParameterProperties {

	/**
	 * Set the project stage to "Development", "UnitTest", "SystemTest", or
	 * "Production".
	 */
	@ServletContextInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)
	private ProjectStage projectStage;

	/**
	 * A space separated list of resource extensions for types that shouldn't be
	 * served by the ResourceHandler implementation. See the specification for
	 * further details.
	 */
	@ServletContextInitParameter(value = ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, listSeparator = ",")
	private List<String> resourceExcludes;

	/**
	 * Semicolon-separated list of view IDs that must save state using the JSF
	 * 1.2-style state saving.
	 */
	@ServletContextInitParameter(value = StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, listSeparator = ";")
	private List<String> fullStateSavingViewIds;

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	@ServletContextInitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME)
	private Boolean partialStateSaving;

	/**
	 * "server" or "client".
	 */
	@ServletContextInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME)
	private String stateSavingMethod;

	/**
	 * Change the default suffix for JSP views.
	 */
	@ServletContextInitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME)
	private String defaultSuffix;

	/**
	 * ViewHandler. Useful for applications that use legacy Facelets
	 * implementation.
	 */
	@ServletContextInitParameter(ViewHandler.DISABLE_FACELET_JSF_VIEWHANDLER_PARAM_NAME)
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The buffer size set on the response.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME)
	private Integer faceletsBufferSize;

	/**
	 * TagDecorator implementations. See javadoc for javax.faces.view.facelets.TagDecorator.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_DECORATORS_PARAM_NAME, listSeparator = ";")
	private List<Class<? extends TagDecorator>> faceletsDecorators;

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsLibraries;

	/**
	 * Time in seconds that facelets should be checked for changes since last
	 * request. A value of -1 disables refresh checking.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME)
	private Integer faceletsRefreshPeriod;

	/**
	 * If true, strip XML comments out of Facelets before delivering to the
	 * client.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_SKIP_COMMENTS_PARAM_NAME)
	private Boolean faceletsSkipComments;

	/**
	 * Set the suffix for Facelet xhtml files.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_SUFFIX_PARAM_NAME)
	private String faceletsSuffix;

	/**
	 * Semicolon-separated list of Facelet files that don't use the default
	 * facelets suffix.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_VIEW_MAPPINGS_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsViewMappings;

	/**
	 * If "true", validate null and empty values. If "auto" validate when
	 * JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	@ServletContextInitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME)
	private String validateEmptyFields;

	/**
	 * The context-param that allows the separator char for clientId strings to be set on a per-web application basis.
	 *
	 * @since 2.0
	 */
	@ServletContextInitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME)
	private String separatorChar;

	@NestedConfigurationProperty
	private final Partial partial = new Partial();

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true) or GMT (if false).
	 */
	@ServletContextInitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME)
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

	@NestedConfigurationProperty
	private final Validator validator = new Validator();

	/**
	 * An implementation of javax.faces.view.facelets.ResourceResolver.
	 * See javadoc for details.
	 */
	@ServletContextInitParameter(ResourceResolver.FACELETS_RESOURCE_RESOLVER_PARAM_NAME)
	private Class<? extends ResourceResolver> faceletsResourceResolver;

	/**
	 * Comma-delimited list of faces config files.
	 */
	@ServletContextInitParameter(value = FacesServlet.CONFIG_FILES_ATTR, listSeparator = ",")
	private List<String> configFiles;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	@ServletContextInitParameter(FacesServlet.LIFECYCLE_ID_ATTR)
	private String lifecycleId;

	/**
	 * If true, consider empty UIInput values to be null instead of empty string.
	 */
	@ServletContextInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL")
	private Boolean interpretEmptyStringSubmittedValuesAsNull;

	/**
	 * Partial class for execute, render and resetValues parameters.
	 */
	@Data
	public static class Partial {

		/**
		 * The request parameter name whose request parameter value is a
		 * {@code Collection} of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Apply Request Values</em>, <em>Process Validations</em>, and
		 * <em>Update Model Values</em> phases of the request processing
		 * lifecycle.
		 *
		 * @since 2.0
		 */
		@ServletContextInitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME)
		private Boolean execute;

		/**
		 * The request parameter name whose request parameter value is a
		 * {@code Collection} of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Render Response</em> phase of the request processing
		 * lifecycle.
		 *
		 * @since 2.0
		 */
		@ServletContextInitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME)
		private Boolean render;
	}

	/**
	 * Valitador class for disableDefaultBeanValidator parameter.
	 */
	@Data
	public static class Validator {

		/**
		 * If "true", disable JSR-303 Bean Validation.
		 */
		@ServletContextInitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME)
		private Boolean disableDefaultBeanValidator;
	}
}
