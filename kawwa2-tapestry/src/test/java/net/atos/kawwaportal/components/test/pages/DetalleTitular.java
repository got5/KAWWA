package net.atos.kawwaportal.components.test.pages;

import java.util.Date;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;

//import net.atos.loyalty.admin.components.DialogoMensaje;
//import net.atos.loyalty.admin.util.ErrorManager;

public class DetalleTitular {
	
	@Property
	private Date date, date2;
	
//	// CLIENT
//	// @Persist(PersistenceConstants.FLASH)
//	@Persist(PersistenceConstants.CLIENT)
//	private Long numeroCarnet;
//
//	public Long getNumeroCarnet() {
//		return numeroCarnet;
//	}
//
//	public void setNumeroCarnet(Long numeroCarnet) {
//		this.numeroCarnet = numeroCarnet;
//	}
//
//	@Property
//	@Inject
//	@Path("context:img/wait.gif")
//	private Asset esperarImagen;
//
//	// Uso futuro
//	@Component(id = "formDatosCaptacion")
//	private Form form;
//
//	@Component(id = "titular_fechaNacimiento")
//	private DateField fechaNacimientoField;
//
//	@Inject
//	private Messages mensajes;
//
//	@Inject
//	private Logger logger;
//
//	@Environmental
//	private ValidationTracker tracker;
//
//	@Persist
//	// @Persist(PersistenceConstants.FLASH)
//	// @Persist(PersistenceConstants.CLIENT)
//	// @SuppressWarnings("unused")
//	@Property
//	private Titular titular;
//
//	@Persist
//	// @Persist(PersistenceConstants.FLASH)
//	@Property
//	private DatosMarketing datosMarketing;
//
//	@Persist
//	// @Persist(PersistenceConstants.FLASH)
//	@Property
//	private Renovacion renovacion;
//
//	// CLIENT
//	@Persist(PersistenceConstants.FLASH)
//	private boolean consultar;
//
//	public void setConsultar(boolean consultar) {
//		this.consultar = consultar;
//	}
//
//	public boolean getConsultar() {
//		return this.consultar;
//	}
//
//	// CLIENT
//	@Persist(PersistenceConstants.FLASH)
//	private boolean primeraLlamada;
//
//	public void setPrimeraLlamada(boolean primeraLlamada) {
//		this.primeraLlamada = primeraLlamada;
//	}
//
//	public boolean getPrimeraLlamada() {
//		return this.primeraLlamada;
//	}
//
//	// @Inject
//	// private Session sesion;
//
//	// @InjectPage
//	// private ListadoTitular index;
//
//	// ------------------------------
//
//	private TitularManager titularManager;
//	private net.atos.xa.resourcelocator.ResourceLocator resourceLocator;
//
//	@Inject
//	private Request request;
//
//	// ========================================================================================
//	// -- EVENTOS DE LA PAGINA
//	// ---------------------------------------------------------------
//	// ========================================================================================
//	@Log
//	void setupRender() {
//		try {
//
//			this.otroTitularVisibilidad = true;
//
//			if (this.listaErrores == null) {
//				this.listaErrores = new ArrayList<String>();
//			}
//			if (this.datosMarketing == null) {
//				this.datosMarketing = new DatosMarketing();
//			}
//
//			if (this.renovacion == null) {
//				this.renovacion = new Renovacion();
//			}
//
//			if (this.renovacion.getFechaCaducidadTarjeta() == null) {
//				this.renovacion.setFechaCaducidadTarjeta(new Date());
//			}
//
//			if (this.titular == null) {
//				this.titular = new Titular();
//				this.titular.setNombre("");
//				this.titular.setApellido2("");
//				this.titular.setApellido1("");
//
//				this.titular.setDni("");
//				this.titular.setNumeroCarnet(new Long("0"));
//				this.titular.setTipoTarjeta("I");
//
//				this.titular.setFechaAlta(new Date());
//				// this.titular.setFechaNacimiento(new Date());
//				this.titular.setFechaSolicitudCuestionario(new Date());
//			} else if (consultar == false && primeraLlamada == true) {
//				this.titular = new Titular();
//				this.titular.setNombre("");
//				this.titular.setApellido2("");
//				this.titular.setApellido1("");
//
//				this.titular.setDni("");
//				this.titular.setNumeroCarnet(new Long("0"));
//				this.titular.setTipoTarjeta("I");
//
//				this.titular.setFechaAlta(new Date());
//				this.titular.setFechaSolicitudCuestionario(new Date());
//				this.primeraLlamada = false;
//				this.renovacion = new Renovacion();
//			}
//
//			if (this.numeroCarnet != null) {
//				this.titular.setNumeroCarnet(this.numeroCarnet);
//
//				TitularManager titularManager = ResourceLocator
//						.lookup(TitularManager.class);
//				this.titular = titularManager.find(this.titular);
//
//				if (this.titular == null) {
//					formNuTarjeta.recordError("El titular no existe.");
//				}
//
//				// this.numeroCarnet=null;
//			}
//
//			if (this.titular.getFechaAlta() != null
//					&& this.titular.getFechaNacimiento() != null) {
//
//				// logger.info("titular.getFechaNacimiento()->"+titular.getFechaNacimiento());
//				titularManager = resourceLocator.lookup(TitularManager.class);
//				String mensajeError = this.titularManager
//						.CALL_TITULARES_WNII(this.titular);
//
//				List errorMessages = new ArrayList();
//				errorMessages
//						.add("Menor de edad. Debe rellenar los datos del tutor.");
//
//				if (mensajeError != null && !mensajeError.equals("")) {
//					this.recordError(mensajeError);
//				} else {
//					logger.error("THIS.CLEAR ERRORS");
//					this.clearErrors(errorMessages, this.listaErrores);
//				}
//			}
//		} catch (Exception e) {
//			logger.info("EXCEPCION: " + e.getMessage());
//		}
//	}
//
//	// -- COMPONENTES DE LA PAGINA:
//	// -----------------------------------------------------------
//	@Property
//	@Inject
//	@Path("context:img/k-theme1/pic_calendar.gif")
//	private Asset assetCalendar;
//
//	@Property
//	private String assetCalendarClientURL;
//
	@Inject
	private AssetSource assetSourceCalendar;
//
//	@Log
	public JSONObject getParamsDatePicker() {
		JSONObject jsonObject = new JSONObject()
		.put("showOn", "button")
		.put("buttonImage", assetSourceCalendar.getClasspathAsset("net/atos/kawwaportal/components/theme/img/k-theme1/pic_calendar.gif").toClientURL())
		.put("buttonImageOnly", true)
		.put("buttonText", "Click to open/close the calendat")
		.put("dateFormat", "dd/mm/yy");
		jsonObject.put("changeYear", true);
		jsonObject.put("changeMonth", true);

		// jsonObject.put("showOtherMonths", true);
		jsonObject.put("selectOtherMonths", true);

		JSONArray meses = new JSONArray();
		meses.put("Enero");
		meses.put("Febrero");
		meses.put("Marzo");
		meses.put("Abril");
		meses.put("Mayo");
		meses.put("Junio");
		meses.put("Julio");
		meses.put("Agosto");
		meses.put("Septiembre");
		meses.put("Octubre");
		meses.put("Noviembre");
		meses.put("Diciembre");

		jsonObject.put("monthNames", meses);

		JSONArray mesesShort = new JSONArray();
		mesesShort.put("Ene");
		mesesShort.put("Feb");
		mesesShort.put("Mar");
		mesesShort.put("Abr");
		mesesShort.put("May");
		mesesShort.put("Jun");
		mesesShort.put("Jul");
		mesesShort.put("Ago");
		mesesShort.put("Sep");
		mesesShort.put("Oct");
		mesesShort.put("Nov");
		mesesShort.put("Dic");

		jsonObject.put("monthNamesShort", mesesShort);

		// usar org.apache.tapestry5.json.JSONArray
		JSONArray dias = new JSONArray();
		dias.put("Lunes");
		dias.put("Martes");
		dias.put("Miercoles");
		dias.put("Jueves");
		dias.put("Viernes");
		dias.put("Sabado");
		dias.put("Domingo");

		jsonObject.put("dayNames", dias);

		JSONArray diasMin = new JSONArray();
		diasMin.put("L");
		diasMin.put("M");
		diasMin.put("X");
		diasMin.put("J");
		diasMin.put("V");
		diasMin.put("S");
		diasMin.put("D");
		jsonObject.put("dayNamesMin", diasMin);
		jsonObject.put("firstDay", 0);

		return jsonObject;
	}
//
//	// NAVEGACION ENTRE PAGINAS: INCOMPATIBLE CON PERSIST
//	/*
//	 * Titular onPassivate() {
//	 * System.out.println("DetalleTitular onPassivate() Inicio" +
//	 * this.titular.getCodigoComercialGA()); //return
//	 * this.titular.getFechaNacimientoString(); return this.titular; }
//	 * 
//	 * 
//	 * void onActivate(String fnacimiento) {
//	 * System.out.println("DetalleTitular onActivate Inicio");
//	 * System.out.println("DetalleTitular onActivate Inicio"+ fnacimiento);
//	 * this.titular = new Titular(); //this.titular = titular;
//	 * 
//	 * }
//	 */
//
//	// ========================================================================================
//	// =============================== EVENTOS Y BOTONES
//	// ======================================
//	// ========================================================================================
//	/*
//	 * @OnEvent(value=EventConstants.ACTION, component="formDatosCaptacion")
//	 * public Object submitFromFormDatosCaptacion()throws Exception{ return
//	 * this; }
//	 */
//	// =============================== VALIDACION FORM
//	// ======================================
//	@Log
//	@OnEvent(value = EventConstants.VALIDATE, component = "formDatosCaptacion")
//	void onValidateFromForm() {
//
//		if (this.botonSeleccionado.equals("insertButton")) {
//			if (form.getHasErrors()) {
//				logger.debug("errores");
//				logger.debug("titular.getNombre()->" + titular.getNombre());
//				// We get here only if a server-side validator detected an
//				// error.
//				// return this;
//			}
//			try {
//
//				TitularQueries titularQueries = ResourceLocator
//						.lookup(TitularQueries.class);
//
//				Long maximoIdTitular = titularQueries.maxId();
//				maximoIdTitular = maximoIdTitular + 1;
//				logger.debug("maximoIdTitular + 1->"
//						+ maximoIdTitular.intValue());
//				this.titular.setNumeroCarnet(maximoIdTitular);
//				this.titular.setEsExtranjero("S");
//				this.titular.setEstadoTarjeta("A");
//				this.titular.setTipoTarjeta("S");
//				this.titular.setCuestionarioCompleto("N");
//				this.titular.setSexo("H");
//
//				logger.info("TitularManager create() em.contains(titular) false");
//				logger.info("TitularManager titular.getNumeroCarnet->"
//						+ titular.getNumeroCarnet());
//				logger.info("TitularManager titular.getCodigoCaptacion->"
//						+ titular.getCodigoCaptacion());
//				logger.info("TitularManager titular.getTipoTarjeta->"
//						+ titular.getTipoTarjeta());
//				logger.info("TitularManager titular.getEstadoTarjeta->"
//						+ titular.getEstadoTarjeta());
//
//				logger.info("TitularManager titular.getSexo->"
//						+ titular.getSexo());
//				logger.info("TitularManager titular.getFechaNacimiento->"
//						+ titular.getFechaNacimientoString());
//				logger.info("TitularManager titular.getEsEmpleado->"
//						+ titular.getEsEmpleado());
//				logger.info("TitularManager titular.getEsExtranjero->"
//						+ titular.getEsExtranjero());
//				logger.info("TitularManager titular.getEsJunior->"
//						+ titular.getEsJunior());
//
//				logger.info("TitularManager titular.getEsPenyista()->"
//						+ titular.getEsPenyista());
//				logger.info("TitularManager titular.getEsPresidentePenya->"
//						+ titular.getEsPresidentePenya());
//				logger.info("TitularManager titular.getEsRelacionesPublicas->"
//						+ titular.getEsRelacionesPublicas());
//				logger.info("TitularManager titular.getEsSocioAnterior->"
//						+ titular.getEsSocioAnterior());
//				logger.info("TitularManager titular.getCuestionarioCompleto->"
//						+ titular.getCuestionarioCompleto());
//
//				if (this.titularManager == null) {
//					logger.info("This.titularManager == null");
//					this.titularManager = resourceLocator
//							.lookup(TitularManager.class);
//				}
//				logger.info("this.titularManager.create(titular)");
//				this.titular = this.titularManager.create(titular);
//				logger.info("this.titularManager.create(titular) fin");
//			} catch (Exception e) {
//				logger.info("onSubmitFromForm() excepcion" + e.getMessage());
//				form.recordError(e.getMessage());
//				// return this;
//			}
//		} else if (this.botonSeleccionado.equals("limpiarButton")) {
//			this.renovacion.LimpiarDatosTarjeta();
//			this.renovacion.LimpiarDatosBancarios();
//			this.renovacion.LimpiarTitular();
//			this.renovacion.LimpiarImporteCuota();
//			logger.info("limpiarButton FIN");
//		} else if (this.botonSeleccionado.equals("limpiarAllButton")) {
//			this.renovacion.LimpiarDatosTarjeta();
//			this.renovacion.LimpiarDatosBancarios();
//			this.renovacion.LimpiarTitular();
//			this.renovacion.LimpiarImporteCuota();
//			this.titular = new Titular();
//			logger.info("limpiarAllButton FIN");
//		} else if (this.botonSeleccionado.equalsIgnoreCase("modifyButton")) {
//			logger.debug("modifica");
//			try {
//
//				this.titularManager = resourceLocator
//						.lookup(TitularManager.class);
//				titularManager.modify(this.titular);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if (this.botonSeleccionado.equalsIgnoreCase("deleteButton")) {
//			logger.info("deleteButton");
//
//			try {
//				logger.debug("try s to remove");
//				this.titularManager = resourceLocator
//						.lookup(TitularManager.class);
//				titularManager.remove(titular);
//				this.titular = null;
//				logger.debug("really removes");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//
//		}
//		// return this;
//	}
//
//	// =============================== SUBMIT FORM
//	// ======================================
//	@Log
//	@OnEvent(value = EventConstants.SUCCESS, component = "formDatosCaptacion")
//	public Object onSuccessFromFormDatosCaptacion() {
//		return this;
//	}
//
//	@Log
//	@OnEvent(value = EventConstants.SUBMIT, component = "formDatosCaptacion")
//	public Object onSubmitFromFormDatosCaptacion() {
//		if (form.getHasErrors()) {
//			System.out.println("onValidateFromForm() errores");
//			// We get here only if a server-side validator detected an error.
//			return this;
//		}
//
//		return this;
//	}
//
//	// =============================== BOTON IncidenciaCuestionario
//	// ======================================
//	private String botonSeleccionado = "";
//
//	@OnEvent(value = EventConstants.SELECTED, component = "insertButton")
//	public Object onSelectedFromInsertButton() {
//		logger.info(" inicio");
//		this.botonSeleccionado = "insertButton";
//		return this;
//
//	}
//
//	@OnEvent(value = EventConstants.SELECTED, component = "modifyButton")
//	public Object onSelectedFromModifyButton() {
//
//		this.botonSeleccionado = "modifyButton";
//		return this;
//	}
//
//	@OnEvent(value = EventConstants.SELECTED, component = "deleteButton")
//	public Object onSelectedFromDeleteButton() {
//		System.out.println("delete");
//		this.botonSeleccionado = "deleteButton";
//		return this;
//	}
//
//	@OnEvent(value = EventConstants.SELECTED, component = "incidenciaCuestionarioButton")
//	public Object onSelectedFromIncidenciaCuestionarioButton() throws Exception {
//
//		logger.info("actionFromIncidenciaCuestionarioButton inicio");
//		this.botonSeleccionado = "incidenciaCuestionarioButton";
//		return this;
//	}
//
//	// =============================== BOTON IncidenciaObservaciones
//	// ======================================
//	@OnEvent(value = EventConstants.SELECTED, component = "observacionesButton")
//	public Object onSelectedFromObservacionesButton() throws Exception {
//
//		logger.info("inicio");
//		this.botonSeleccionado = "observacionesButton";
//
//		return this;
//	}
//
//	// =============================== BOTON LIMPIAR
//	// ======================================
//	@OnEvent(value = EventConstants.SELECTED, component = "limpiarButton")
//	public Object onSelectedFromLimpiarButton() throws Exception {
//
//		logger.info("inicio");
//		this.botonSeleccionado = "limpiarButton";
//		logger.info(" ->" + this.botonSeleccionado);
//
//		return this;
//	}
//
//	@OnEvent(value = EventConstants.SELECTED, component = "limpiarAllButton")
//	public Object onSelectedFromLimpiarAllButton() throws Exception {
//
//		logger.info("inicio");
//		this.botonSeleccionado = "limpiarAllButton";
//		logger.info(" ->" + this.botonSeleccionado);
//
//		return this;
//	}
//
//	// ========================================================================================
//	// =============================== LOV
//	// ====================================================
//	// ========================================================================================
//	public Object onVolverPenia(String penia) {
//		if (this.titular == null) {
//			this.titular = new Titular();
//		}
//		this.titular.setNombrePenya(penia);
//		return this;
//	}
//
//	public Object onVolverComercial(String usuario) {
//		if (this.titular == null) {
//			this.titular = new Titular();
//		}
//		this.titular.setCodigoComercialGA(usuario);
//		return this;
//	}
//
//	public Object onVolverJugadorFavorito(String jugadorFavorito) {
//		if (this.titular == null) {
//			this.titular = new Titular();
//		}
//		this.titular.setJugadorFavorito(jugadorFavorito);
//		return this;
//	}
//
//	public Object onVolverNacionalidades(String paisNacimiento) {
//		if (this.titular == null) {
//			this.titular = new Titular();
//		}
//		this.titular.setPaisNacimiento(paisNacimiento);
//		return this;
//	}
//
//	// ==========================================================
//	// CAMPOS VISIBLES
//	// ==========================================================
//
//	// ========OTRO TITULAR==========================================
//	@InjectComponent
//	private Zone otroTitularZone;
//
//	Object onValueChangedFromRenovacion_tipoTitular(String renovacionTipoTitular) {
//		this.renovacion.setTipoTitular(renovacionTipoTitular);
//
//		if (request.isXHR()) {
//			return otroTitularZone.getBody();
//		} else {
//			return null;
//		}
//	}
//
//	private Boolean otroTitularVisibilidad;
//
//	public Boolean getOtroTitularVisibilidad() {
//		if (this.renovacion != null && this.renovacion.getTipoTitular() != null
//				&& this.renovacion.getTipoTitular().equals("O")) {
//			this.otroTitularVisibilidad = true;
//		} else {
//			this.otroTitularVisibilidad = false;
//		}
//		return this.otroTitularVisibilidad;
//	}
//
//	// ========TIPO PAGO==========================================
//	@InjectComponent
//	private Zone tarjetaZone;
//
//	Object onValueChangedFromRenovacion_tipoPago(String renovacionTipoPago) {
//		this.renovacion.setTipoPago(renovacionTipoPago);
//
//		return request.isXHR() ? tarjetaZone.getBody() : null;
//	}
//
//	// ==========================================================
//	private Boolean tarjetaCuentaTitularVisibilidad;
//
//	public Boolean getTarjetaCuentaTitularVisibilidad() {
//
//		if ((this.renovacion != null && this.renovacion.getTipoPago() != null
//				&& this.titular != null
//				&& this.titular.getCodigoCaptacion() != null && ((this.renovacion
//				.getTipoPago().equals("S")
//				&& this.titular.getCodigoCaptacion() != 1
//				&& this.titular.getCodigoCaptacion() != 2
//				&& this.titular.getCodigoCaptacion() != 3
//				&& this.titular.getCodigoCaptacion() != 4 && this.titular
//				.getCodigoCaptacion() != 7) || ((this.renovacion.getTipoPago()
//				.equals("D") || this.renovacion.getTipoPago().equals("T")) && (this.titular
//				.getCodigoCaptacion() == 1 || this.titular.getCodigoCaptacion() == 2))))
//				|| (this.renovacion != null
//						&& this.renovacion.getTipoPago() != null && this.renovacion
//						.getTipoPago().equals("E"))
//				|| (this.renovacion != null
//						&& this.renovacion.getTipoPago() != null && this.renovacion
//						.getTipoPago().equals("X"))
//				|| (this.renovacion != null
//						&& this.renovacion.getTipoPago() != null && this.renovacion
//						.getTipoPago().equals("F"))) {
//
//			this.renovacion.LimpiarDatosTarjeta();
//			this.renovacion.LimpiarDatosBancarios();
//			this.renovacion.LimpiarTitular();
//
//			this.tarjetaCuentaTitularVisibilidad = false;
//		} else {
//			this.tarjetaCuentaTitularVisibilidad = true;
//		}
//		return this.tarjetaCuentaTitularVisibilidad;
//	}
//
//	public void setTarjetaCuentaTitularVisibilidad(Boolean visibilidad) {
//		this.tarjetaCuentaTitularVisibilidad = visibilidad;
//	}
//
//	// ==========================================================
//	private Boolean cuentaVisibilidad;
//
//	public Boolean getCuentaVisibilidad() {
//
//		if (this.renovacion != null && this.renovacion.getTipoPago() != null
//				&& this.renovacion.getTipoPago().equals("T")) {
//
//			this.renovacion.LimpiarDatosBancarios();
//
//			this.cuentaVisibilidad = false;
//		} else {
//			this.cuentaVisibilidad = true;
//		}
//		return this.cuentaVisibilidad;
//	}
//
//	public void setCuentaVisibilidad(Boolean visibilidad) {
//		this.cuentaVisibilidad = visibilidad;
//	}
//
//	// ==========================================================
//	private Boolean tarjetaVisibilidad;
//
//	public Boolean getTarjetaVisibilidad() {
//		if (this.renovacion != null && this.renovacion.getTipoPago() != null
//				&& this.renovacion.getTipoPago().equals("D")) {
//
//			this.renovacion.LimpiarDatosTarjeta();
//
//			this.tarjetaVisibilidad = false;
//		} else {
//			this.tarjetaVisibilidad = true;
//		}
//		return this.tarjetaVisibilidad;
//	}
//
//	public void setTarjetaVisibilidad(Boolean visibilidad) {
//		this.tarjetaVisibilidad = visibilidad;
//	}
//
//	// ==========================================================
//
//	// ==========================================================
//	// VALIDACIONES AJAX
//	// ==========================================================
//
//	// @Property
//	// private String mensajeError;
//	@Log
//	JSONObject onAjaxValidateFromTitular_fechaNacimiento() {
//		JSONObject jsonObject = new JSONObject();
//		try {
//			if (request.getParameter("param") != null) {
//				this.titular.setFechaNacimientoString(request
//						.getParameter("param"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_captacion") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				logger.info("titular_captacion->"
//						+ request.getParameter("titular_captacion"));
//				Integer codigoCaptacionAux = new Integer(
//						request.getParameter("titular_captacion"));
//				logger.info("titular_captacion->->"
//						+ codigoCaptacionAux.toString());
//				this.titular.setCodigoCaptacion(codigoCaptacionAux);
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_pais") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				this.titular.setPais(request.getParameter("titular_pais"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_esRelacionesPublicas") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				this.titular.setEsRelacionesPublicas(request
//						.getParameter("titular_esRelacionesPublicas"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_esEmpleado") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				this.titular.setEsEmpleado(request
//						.getParameter("titular_esEmpleado"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_tipoInternacional") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				this.titular.setTipoInternacional(request
//						.getParameter("titular_tipoInternacional"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//			if (request.getParameter("titular_esJunior") != null
//					&& !request.getParameter("titular_captacion").equals("")) {
//				this.titular.setEsJunior(request
//						.getParameter("titular_esJunior"));
//				logger.info("titular.getFechaNacimiento->"
//						+ this.titular.getFechaNacimientoString());
//			}
//
//			logger.info("antes de CorePackage.getTitularManager");
//			titularManager = ResourceLocator.lookup(TitularManager.class);
//
//			logger.info("despues de CorePackage.getTitularManager");
//
//			this.titular.setFechaAlta(new Date());
//			String mensajeError = "";
//			logger.info("CALL_RMFO0090_TITULARES_FENACIMI_PR");
//			mensajeError = this.titularManager
//					.CALL_RMFO0090_TITULARES_FENACIMI_PR(this.titular,
//							this.renovacion);
//
//			// ErrorManager errorManager = new ErrorManager();
//			List errorMessages = new ArrayList();
//			errorMessages
//					.add("No pueden darse de alta menores de 18 años con captación Internet");
//			errorMessages
//					.add("Menor de edad. Debe rellenar los datos del tutor.");
//			errorMessages
//					.add("La promoción seleccionada no es válida para menores de 15 años");
//			errorMessages
//					.add("La promoción seleccionada no es válida para mayores de 14 años");
//			errorMessages
//					.add("La fecha de nacimiento debe estar comprendida entre 1900 y la fecha actual");
//			errorMessages.add("No hay conexion con la base de datos");
//			errorMessages.add("100 - ORA-01403: no data found");
//			errorMessages
//					.add("Error de BD validando Fecha nacimiento: 1 - User-Defined Exception");
//
//			if (mensajeError != null && !mensajeError.equals("")) {
//				this.clearErrors(errorMessages, this.listaErrores);
//				// this.form.recordError(mensajeError);
//				this.listaErrores.add(mensajeError);
//				jsonObject.put("error", mensajeError);
//			} else {
//				// logger.info("this.clearError()");
//				this.clearErrors(errorMessages, this.listaErrores);
//			}
//
//			logger.info("return JSON");
//
//			if (this.titular.getEsJunior() != null
//					&& !this.titular.getEsJunior().equals("")) {
//				logger.info("this.recordError() this.titular.getEsJunior().toString()->"
//						+ this.titular.getEsJunior().toString());
//				jsonObject.put("titular_esJunior", this.titular.getEsJunior());
//			} else {
//				jsonObject.put("titular_esJunior", "");
//			}
//
//			if (this.titular.getCodigoCaptacion() != null
//					&& !this.titular.getCodigoCaptacion()
//							.equals(new Integer(0))) {
//				logger.info("this.recordError() this.titular.getCodigoCaptacion().toString()->"
//						+ this.titular.getCodigoCaptacion().toString());
//				jsonObject.put("titular_captacion", this.titular
//						.getCodigoCaptacion().toString());
//			} else {
//				jsonObject.put("titular_captacion", "");
//			}
//
//			if (this.titular.getPais() != null
//					&& !this.titular.getPais().equals("")) {
//				jsonObject.put("titular_pais", this.titular.getPais());
//			} else {
//				jsonObject.put("titular_pais", "");
//			}
//
//			if (this.titular.getTipoInternacional() != null
//					&& !this.titular.getTipoInternacional().equals("")) {
//				jsonObject.put("titular_tipoInternacional",
//						this.titular.getTipoInternacional());
//			} else {
//				jsonObject.put("titular_tipoInternacional", "");
//			}
//
//			if (this.titular.getEsEmpleado() != null
//					&& !this.titular.getEsEmpleado().equals("")) {
//				jsonObject.put("titular_esEmpleado",
//						this.titular.getEsEmpleado());
//			} else {
//				jsonObject.put("titular_esEmpleado", "");
//			}
//
//			if (this.titular.getEsEmpleado() != null
//					&& !this.titular.getEsEmpleado().equals("")) {
//				jsonObject.put("titular_esRelacionesPublicas",
//						this.titular.getEsEmpleado());
//			} else {
//				jsonObject.put("titular_esRelacionesPublicas", "");
//			}
//
//			if (this.renovacion.getImporteCuota() != null
//					&& !this.renovacion.getImporteCuota().equals(
//							new Integer(-1))) {
//				logger.info("this.renovacion.getImporteCuota()->"
//						+ this.renovacion.getImporteCuota().toString());
//				jsonObject.put("renovacion_importeCuota", this.renovacion
//						.getImporteCuota().toString());
//			} else {
//				jsonObject.put("renovacion_importeCuota", "");
//			}
//			/*
//			 * if (this.renovacion.getRegistroInterno()!=null &&
//			 * !this.renovacion.getRegistroInterno().equals("")){
//			 * jsonObject.put("renovacion_registroInterno",
//			 * this.renovacion.getRegistroInterno()); } else {
//			 * jsonObject.put("renovacion_registroInterno", ""); }
//			 */
//			/*
//			 * logger.info("CALL_TITULARES_WNII inicio");
//			 * if(this.titular.getFechaAlta()!=null &&
//			 * this.titular.getFechaNacimiento()!=null ){ //titularManager =
//			 * resourceLocator.lookup(TitularManager.class);
//			 * logger.info("CALL_TITULARES_WNII"); String mensajeErrorInit =
//			 * this.titularManager.CALL_TITULARES_WNII(this.titular);
//			 * 
//			 * logger.info("CALL_TITULARES_WNII mensajeErrorInit->"+mensajeErrorInit
//			 * ); List errorMessagesInit = new ArrayList();
//			 * errorMessagesInit.add
//			 * ("Menor de edad. Debe rellenar los datos del tutor.");
//			 * 
//			 * if (mensajeErrorInit != null && !mensajeErrorInit.equals("")) {
//			 * 
//			 * this.recordError(mensajeErrorInit); } else {
//			 * this.clearErrors(errorMessagesInit); }
//			 * 
//			 * }
//			 */
//			logger.info("fin return json");
//
//			return jsonObject;
//		} catch (Exception e) {
//			logger.info("Excepcion: " + e.getMessage());
//			return jsonObject.put("error", "Excepcion: " + e.getMessage());
//		}
//
//	}
//
//	@Log
//	JSONObject onAjaxValidateCaptacionFromTitular_captacion() {
//
//		JSONObject jsonObject = new JSONObject();
//		try {
//
//			if (request.getParameter("param") != null) {
//				this.titular.setCodigoCaptacion(new Integer(request
//						.getParameter("param")));
//				logger.info("this.titular.setCodigoCaptacion()->"
//						+ this.titular.getCodigoCaptacion().toString());
//			}
//			if (request.getParameter("titular_fechaNacimiento") != null
//					&& !request.getParameter("titular_fechaNacimiento").equals(
//							"")) {
//
//				Date fechaNacimiento = new Date(
//						request.getParameter("titular_fechaNacimiento"));
//
//				this.titular.setFechaNacimiento(fechaNacimiento);
//				logger.info("this.titular.setFechaNacimiento()->"
//						+ this.titular.getFechaNacimiento().toString());
//			}
//			if (request.getParameter("titular_esJunior") != null
//					&& !request.getParameter("titular_esJunior").equals("")) {
//				this.titular.setEsJunior(request
//						.getParameter("titular_esJunior"));
//				logger.info("this.titular.setEsJunior()->"
//						+ this.titular.getEsJunior());
//			}
//			logger.info("renovacion_tipoPago");
//			if (request.getParameter("renovacion_tipoPago") != null
//					&& !request.getParameter("renovacion_tipoPago").equals("")) {
//				this.renovacion.setTipoPago(request
//						.getParameter("renovacion_tipoPago"));
//				logger.info("this.renovacion.setTipoPago()->"
//						+ this.renovacion.getTipoPago());
//			}
//			this.titular.setFechaAlta(new Date());
//			String mensajeError = "";
//
//			this.titularManager = ResourceLocator.lookup(TitularManager.class);
//
//			mensajeError = this.titularManager.CALL_TITULARES_LOV_CAPTACION(
//					this.titular, this.renovacion);
//
//			// ErrorManager errorManager = new ErrorManager();
//			List errorMessages = new ArrayList();
//			errorMessages
//					.add("La captacion BANESTO requiere verificación del DNI.");
//			errorMessages
//					.add("La promoción es de regalo únicamente. Dar de alta al Titular que recibe el regalo con opción de menú correspondiente.");
//			errorMessages
//					.add("Esta promoción no es válida para menores de 15 años");
//			errorMessages
//					.add("Forma de pago no compatible con la captación seleccionada");
//			errorMessages.add("Transferencia solo para peñistas");
//			errorMessages
//					.add("Forma de captación pertenece a una promoción no vigente");
//			errorMessages
//					.add("Forma de captación pertenece a una promoción Web");
//			errorMessages
//					.add("Se han agotado las entradas gratuitas disponibles para este partido.");
//			errorMessages.add("100 - ORA-01403: no data found");
//
//			if (mensajeError != null && !mensajeError.equals("")) {
//				this.clearErrors(errorMessages, this.listaErrores);
//				this.listaErrores.add(mensajeError);
//				// this.form.recordError(mensajeError);
//				jsonObject.put("error", mensajeError);
//			} else {
//				this.clearErrors(errorMessages, this.listaErrores);
//			}
//
//			if (this.titular.getCodigoCaptacion() != null
//					&& !this.titular.getCodigoCaptacion()
//							.equals(new Integer(0))) {
//				logger.info("this.titular.getCodigoCaptacion()->"
//						+ this.titular.getCodigoCaptacion());
//				jsonObject.put("titular_captacion",
//						this.titular.getCodigoCaptacion());
//			} else {
//				logger.info("titular_captacion->''");
//				jsonObject.put("titular_captacion", "");
//			}
//
//			if (this.renovacion.getTipoPago() != null
//					&& !this.renovacion.getTipoPago().equals("")) {
//				logger.info("this.renovacion.getTipoPago()->"
//						+ this.renovacion.getTipoPago());
//				jsonObject.put("renovacion_tipoPago",
//						this.renovacion.getTipoPago());
//			} else {
//				logger.info("renovacion_tipoPago->''");
//				jsonObject.put("renovacion_tipoPago", "");
//			}
//
//			logger.info("getFechaSolicitudCuestionario");
//			if (this.titular.getFechaSolicitudCuestionario() != null) {
//				logger.info("this.titular.getFechaSolicitudCuestionario()->"
//						+ this.titular.getFechaSolicitudCuestionario()
//								.toString());
//				jsonObject
//						.put("titular_fechaSolicitudCuestionario", this.titular
//								.getFechaSolicitudCuestionario().toString());
//			} else {
//				logger.info("this.titular.getFechaSolicitudCuestionario()-> vacio");
//				jsonObject.put("titular_fechaSolicitudCuestionario", "");
//			}
//
//			if (this.titular.getEsPresidentePenya() != null
//					&& !this.titular.getEsPresidentePenya().equals("")) {
//				logger.info("this.titular.getEsPresidentePenya()"
//						+ this.titular.getEsPresidentePenya());
//				jsonObject.put("titular_esPresidentePenya",
//						this.titular.getEsPresidentePenya());
//			} else {
//				logger.info("titular_esPresidentePenya->''");
//				jsonObject.put("titular_esPresidentePenya", "");
//			}
//
//			if (this.titular.getEsEmpleado() != null
//					&& !this.titular.getEsEmpleado().equals("")) {
//				logger.info("this.titular.getEsEmpleado()"
//						+ this.titular.getEsEmpleado());
//				jsonObject.put("titular_esEmpleado",
//						this.titular.getEsEmpleado());
//			} else {
//				logger.info("titular_esEmpleado->''");
//				jsonObject.put("titular_esEmpleado", "");
//			}
//
//			if (this.titular.getEsRelacionesPublicas() != null
//					&& !this.titular.getEsRelacionesPublicas().equals("")) {
//				logger.info("this.titular.getEsRelacionesPublicas()"
//						+ this.titular.getEsRelacionesPublicas());
//				jsonObject.put("titular_esRelacionesPublicas",
//						this.titular.getEsRelacionesPublicas());
//			} else {
//				logger.info("titular_esRelacionesPublicas->''");
//				jsonObject.put("titular_esRelacionesPublicas", "");
//			}
//
//			/*
//			 * if (this.titular.getSiDomiciliacion()!=null &&
//			 * !this.titular.getSiDomiciliacion().equals("")){
//			 * jsonObject.put("", this.titular.getSiDomiciliacion()); } else {
//			 * jsonObject.put("", ""); }
//			 */
//
//			if (this.renovacion.getEntidadBancaria() != null
//					&& !this.renovacion.getEntidadBancaria().equals(
//							new Integer(0))) {
//				logger.info("this.renovacion.getEntidadBancaria().toString()->"
//						+ this.renovacion.getEntidadBancaria().toString());
//				jsonObject.put("renovacion_entidadBancaria", this.renovacion
//						.getEntidadBancaria().toString());
//			} else {
//				logger.info("renovacion_entidadBancaria->''");
//				jsonObject.put("renovacion_entidadBancaria", "");
//			}
//
//			if (this.renovacion.getSucursalBancaria() != null
//					&& !this.renovacion.getSucursalBancaria().equals(
//							new Integer(0))) {
//				logger.info("this.renovacion.getSucursalBancaria().toString()->"
//						+ this.renovacion.getSucursalBancaria().toString());
//				jsonObject.put("renovacion_sucursalBancaria", this.renovacion
//						.getSucursalBancaria().toString());
//			} else {
//				logger.info("this.renovacion.getSucursalBancaria() is null => ''");
//				jsonObject.put("renovacion_sucursalBancaria", "");
//			}
//
//			if (this.renovacion.getDigitoControl() != null
//					&& !this.renovacion.getDigitoControl().equals(
//							new Integer(0))) {
//				logger.info("this.renovacion.getDigitoControl().toString()->"
//						+ this.renovacion.getDigitoControl().toString());
//				jsonObject.put("renovacion_digitoControl", this.renovacion
//						.getDigitoControl().toString());
//			} else {
//				logger.info("renovacion_digitoControl->''");
//				jsonObject.put("renovacion_digitoControl", "");
//			}
//
//			if (this.renovacion.getNumeroCuenta() != null
//					&& !this.renovacion.getNumeroCuenta()
//							.equals(new Integer(0))) {
//				logger.info("this.renovacion.getNumeroCuenta().toString()->"
//						+ this.renovacion.getNumeroCuenta().toString());
//				jsonObject.put("renovacion_numeroCuenta", this.renovacion
//						.getNumeroCuenta().toString());
//			} else {
//				logger.info("renovacion_numeroCuenta->''");
//				jsonObject.put("renovacion_numeroCuenta", "");
//			}
//
//			if (this.renovacion.getTipoTitular() != null
//					&& !this.renovacion.getTipoTitular().equals("")) {
//				logger.info("this.renovacion.getTipoTitular()->"
//						+ this.renovacion.getTipoTitular());
//				jsonObject.put("renovacion_tipoTitular",
//						this.titular.getEsRelacionesPublicas());
//			} else {
//				logger.info("renovacion_tipoTitular->''");
//				jsonObject.put("renovacion_tipoTitular", "");
//			}
//
//			if (this.renovacion.getNombreTitularCuentaTarjeta() != null
//					&& !this.renovacion.getNombreTitularCuentaTarjeta().equals(
//							"")) {
//				logger.info("this.renovacion.getNombreTitularCuentaTarjeta()->"
//						+ this.renovacion.getNombreTitularCuentaTarjeta());
//				jsonObject.put("renovacion_nombreTitularCuentaTarjeta",
//						this.renovacion.getNombreTitularCuentaTarjeta());
//			} else {
//				logger.info("renovacion_nombreTitularCuentaTarjeta->''");
//				jsonObject.put("renovacion_nombreTitularCuentaTarjeta", "");
//			}
//
//			if (this.renovacion.getPrimerApellidoTitularCuentaTarjeta() != null
//					&& !this.renovacion.getPrimerApellidoTitularCuentaTarjeta()
//							.equals("")) {
//				logger.info("this.renovacion.getNombreTitularCuentaTarjeta()->"
//						+ this.renovacion.getNombreTitularCuentaTarjeta());
//				jsonObject
//						.put("renovacion_primerApellidoTitularCuentaTarjeta",
//								this.renovacion
//										.getPrimerApellidoTitularCuentaTarjeta());
//			} else {
//				logger.info("renovacion_primerApellidoTitularCuentaTarjeta->''");
//				jsonObject.put("renovacion_primerApellidoTitularCuentaTarjeta",
//						"");
//			}
//
//			if (this.renovacion.getSegundoApellidoTitularCuentaTarjeta() != null
//					&& !this.renovacion
//							.getSegundoApellidoTitularCuentaTarjeta()
//							.equals("")) {
//				logger.info("this.renovacion.getSegundoApellidoTitularCuentaTarjeta()->"
//						+ this.renovacion
//								.getSegundoApellidoTitularCuentaTarjeta());
//				jsonObject.put(
//						"renovacion_segundoApellidoTitularCuentaTarjeta",
//						this.renovacion
//								.getSegundoApellidoTitularCuentaTarjeta());
//			} else {
//				logger.info("renovacion_segundoApellidoTitularCuentaTarjeta->''");
//				jsonObject.put(
//						"renovacion_segundoApellidoTitularCuentaTarjeta", "");
//			}
//
//			if (this.renovacion.getDNITitular() != null
//					&& !this.renovacion.getDNITitular().equals("")) {
//				logger.info("this.renovacion.getDNITitular()->"
//						+ this.renovacion.getDNITitular());
//				jsonObject.put("titular_dni_v2",
//						this.renovacion.getDNITitular());
//			} else {
//				logger.info("titular_dni_v2->''");
//				jsonObject.put("titular_dni_v2", "");
//			}
//
//			if (this.renovacion.getNumeroTarjeta() != null
//					&& !this.renovacion.getNumeroTarjeta().equals(
//							new Integer(-1))) {
//				logger.info("this.renovacion.getNumeroTarjeta().toString()->"
//						+ this.renovacion.getNumeroTarjeta().toString());
//				jsonObject.put("renovacion_numeroTarjeta", this.renovacion
//						.getNumeroTarjeta().toString());
//			} else {
//				logger.info("renovacion_numeroTarjeta->''");
//				jsonObject.put("renovacion_numeroTarjeta", "");
//			}
//
//			if (this.renovacion.getFechaCaducidadTarjeta() != null) {
//				logger.info("this.renovacion.getNumeroTarjeta().toString()->"
//						+ this.renovacion.getNumeroTarjeta().toString());
//				jsonObject.put("renovacion_fechaCaducidadTarjeta",
//						this.renovacion.getNumeroTarjeta().toString());
//			} else {
//				logger.info("renovacion_fechaCaducidadTarjeta->''");
//				jsonObject.put("renovacion_fechaCaducidadTarjeta", "");
//			}
//
//			if (this.renovacion.getImporteCuota() != null
//					&& !this.renovacion.getImporteCuota().equals(
//							new Integer(-1))) {
//				logger.info("this.renovacion.getImporteCuota().toString()->"
//						+ this.renovacion.getImporteCuota().toString());
//				jsonObject.put("renovacion_importeCuota", this.renovacion
//						.getImporteCuota().toString());
//			} else {
//				logger.info("renovacion_importeCuota->''");
//				jsonObject.put("renovacion_importeCuota", "");
//			}
//			/*
//			 * if (this.renovacion.getRegistroInterno()!=null &&
//			 * !this.renovacion.getRegistroInterno().equals("")){
//			 * jsonObject.put("renovacion_registroInterno",
//			 * this.renovacion.getRegistroInterno()); } else {
//			 * jsonObject.put("renovacion_registroInterno", ""); }
//			 */
//			// logger.info("fin return json");
//			return jsonObject;
//		} catch (Exception e) {
//			logger.info("Excepcion: " + e.getMessage());
//			return jsonObject.put("error",
//					"Excepcion en captación: " + e.getMessage());
//		}
//
//	}
//
//	@Log
//	JSONObject onAjaxActualizarCampoFromTitular_pais() {
//		this.titular.setPais(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	JSONObject onAjaxActualizarCampoFromTitular_esRelacionesPublicas() {
//		this.titular.setEsRelacionesPublicas(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	JSONObject onAjaxActualizarCampoFromTitular_esEmpleado() {
//		this.titular.setEsEmpleado(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	JSONObject onAjaxActualizarCampoFromTitular_tipoInternacional() {
//		this.titular.setTipoInternacional(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	JSONObject onAjaxValidateFromTitular_esJunior() {
//		this.titular.setEsJunior(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	@Log
//	JSONObject onAjaxActualizarCampoFromTitular_telefono() {
//		this.titular.setTelefono(request.getParameter("param"));
//
//		return new JSONObject();
//	}
//
//	@Log
//	JSONObject onAjaxActualizarCampoFromTitular_numeroTelefonoAlternativo() {
//		JSONObject jsonObject = new JSONObject();
//		try {
//
//			this.titular.setNumeroTelefonoAlternativo(request
//					.getParameter("param"));
//			this.titular.setPaisNacimiento(request.getParameter("param"));
//
//			titularManager = resourceLocator.lookup(TitularManager.class);
//
//			String mensajeError = "";
//
//			mensajeError = this.titularManager
//					.CALL_RMFO0090_TITULARES_NUTELEFO_ALT(this.titular);
//			if (!mensajeError.equals("")) {
//				// this.form.recordError(mensajeError);
//				// this.clearErrors(errorMessages,this.listaErrores);
//				this.listaErrores.add(mensajeError);
//				jsonObject.put("error", mensajeError);
//			}
//
//		} catch (Exception e) {
//			return jsonObject.put("error", "Excepcion: " + e.getMessage());
//		}
//		return jsonObject;
//	}
//
//	@Log
//	JSONObject onAjaxActualizarCampoFromTitular_email() {
//		JSONObject jsonObject = new JSONObject();
//		try {
//			this.titular.setEmail(request.getParameter("param"));
//
//			titularManager = resourceLocator.lookup(TitularManager.class);
//
//			String mensajeError = "";
//
//			mensajeError = this.titularManager
//					.CALL_RMFO0090_TITULARES_NBCORREO(this.titular);
//			if (!mensajeError.equals("")) {
//				// this.form.recordError(mensajeError);
//				// this.recordError(mensajeError);
//				// this.clearErrors(errorMessages,this.listaErrores);
//				this.listaErrores.add(mensajeError);
//				jsonObject.put("error", mensajeError);
//			}
//		} catch (Exception e) {
//			return jsonObject.put("error", "Excepcion: " + e.getMessage());
//		}
//		return jsonObject;
//	}
//
//	// LOVS----------------------------------------------------------------------------------
//
//	@Component
//	@Property
//	private Zone zonaPaisVolver;
//
//	@OnEvent(value = EventConstants.ACTION, component = "modalPais")
//	Object onActionFromModalPais() {
//		try {
//			if (!request.isXHR()) {
//				return this;
//			} else {
//				return zonaPaisVolver;
//			}
//		} catch (Exception e) {
//			logger.info("ERROR: " + e.getMessage());
//			return null;
//		}
//	}
//
//	@Component
//	@Property
//	private Zone zonaCaptacionVolver;
//
//	@OnEvent(value = EventConstants.ACTION, component = "modalCaptacion")
//	Object onActionFromModalCaptacion() {
//		try {
//			if (!request.isXHR()) {
//				return this;
//			} else {
//				return zonaCaptacionVolver;
//			}
//		} catch (Exception e) {
//			logger.info("ERROR: " + e.getMessage());
//			return null;
//		}
//	}
//
//	// ================= GESTION DE
//	// ERRORES===========================================
//	@Log
//	public void recordError(String error) {
//		if (this.form != null) {
//			if (this.form.getDefaultTracker() != null
//					&& this.form.getDefaultTracker().getHasErrors()) {
//				logger.debug("hay errores en el tracker");
//				List<String> listaErrores = this.form.getDefaultTracker()
//						.getErrors();
//				logger.debug("listaErrores.size()->" + listaErrores.size());
//				if (!listaErrores.contains(error)) {
//					this.form.recordError(error);
//					logger.debug("REGISTRADO EL ERROR recordError->" + error);
//				}
//			} else {
//
//				this.form.recordError(error);
//				logger.debug("REGISTRADO EL ERROR recordError error->" + error);
//			}
//		}
//	}
//
//	@Log
//	public void clearErrorsDefaultTracker(List<String> errorMessages) {
//		if (this.form != null && this.form.getDefaultTracker() != null
//				&& this.form.getDefaultTracker().getErrors() != null) {
//			List listaErrores = this.form.getDefaultTracker().getErrors();
//			logger.debug("listaErrores.size()->" + listaErrores.size()
//					+ ". Que son:");
//			Iterator<String> iteradorErrorMensajes = errorMessages.iterator();
//			while (iteradorErrorMensajes.hasNext()) {
//				String currentMessage = iteradorErrorMensajes.next();
//				logger.debug("POSIBLE ERROR REPETIDO currentMessage->"
//						+ currentMessage);
//				if (listaErrores.contains(currentMessage)) {
//
//					listaErrores.remove(currentMessage);
//					logger.debug("SE HA BORRADO EL ERROR currentMessage->"
//							+ currentMessage);
//					this.form.getDefaultTracker().clear();
//					Iterator<String> iteradorErrores = listaErrores.iterator();
//					while (iteradorErrores.hasNext()) {
//						String currentError = iteradorErrores.next().toString();
//						logger.debug("RECOMPONIENDO ERRORES currentError->"
//								+ currentError);
//						this.form.getDefaultTracker().recordError(currentError);
//					}
//				}
//			}
//		} else {
//			logger.debug("INTENTA this.form.getDefaultTracker().clear();");
//			if (this.form != null && this.form.getDefaultTracker() != null) {
//				logger.debug("POR SEGURIDAD this.form.getDefaultTracker().clear();");
//				this.form.getDefaultTracker().clear();
//			}
//		}
//	}
//
//	@Log
//	public void clearErrors(List<String> errorMessages,
//			List<String> listaErrores) {
//		Iterator<String> iteradorErrorMensajes = errorMessages.iterator();
//		while (iteradorErrorMensajes.hasNext()) {
//			String currentMessage = iteradorErrorMensajes.next();
//
//			if (listaErrores.contains(currentMessage)) {
//				listaErrores.remove(currentMessage);
//			}
//		}
//
//	}
//
//	// ===========================================Romeo=============================================================
//
//	@Component(id = "formNuTarjeta")
//	private Form formNuTarjeta;
//
//	@OnEvent(value = EventConstants.VALIDATE, component = "formNuTarjeta")
//	public void validation() {
//		TitularManager titularManager = ResourceLocator
//				.lookup(TitularManager.class);
//		this.titular = titularManager.find(this.titular);
//
//		if (this.titular == null) {
//			formNuTarjeta.recordError("El titular no existe.");
//		}
//	}
//
//	@OnEvent(value = EventConstants.SUCCESS, component = "formNuTarjeta")
//	public Object buscarSuccess() {
//		return this;
//	}
//
//	/*
//	 * @OnEvent(value=EventConstants.FAILURE, component="FormNuTarjeta") public
//	 * void buscarFailure(){
//	 * 
//	 * 
//	 * }
//	 */
//
//	// ===========================================Actualizar errores
//	// periodicamente=============================================================
//
//	@Property
//	private String currentError;
//
//	/*
//	 * @Log public List<String> getErrors(){ return
//	 * form.getDefaultTracker().getErrors(); }
//	 * 
//	 * @Log public Boolean getError(){ if(form != null &&
//	 * form.getDefaultTracker()!=null){ return
//	 * form.getDefaultTracker().getHasErrors(); } else { return false; } }
//	 */
//	@Persist
//	private List<String> listaErrores;
//
//	@Log
//	public List<String> getErrors() {
//		return this.listaErrores;
//	}
//
//	@Log
//	public Boolean getError() {
//		if (listaErrores != null) {
//			return !listaErrores.isEmpty();
//		} else {
//			return false;
//		}
//	}
//
//	@InjectComponent
//	private Zone timeZone;
//
//	@Log
//	Object onRefreshTimeZone() {
//		return request.isXHR() ? timeZone.getBody() : null;
//	}
//
//	public Date getServerTime() {
//		return new Date();
//	}
//
//	// ENLACE VOLVER
//	@InjectPage
//	private ListadoTitular listadoTitularPage;
//
//	Object onActionFromVolverLink() {
//
//		Titular titular = new Titular();
//		if (this.getNumeroCarnet() != null) {
//			titular.setNumeroCarnet(this.getNumeroCarnet());
//		}
//		listadoTitularPage.inicializarTitular(titular);
//
//		return listadoTitularPage;
//	}
}




