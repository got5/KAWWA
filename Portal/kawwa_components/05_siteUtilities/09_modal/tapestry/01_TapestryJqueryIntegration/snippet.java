
	@Property
	private JSONObject params;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void onActivate(){
		params=new JSONObject();
		params.accumulate("modal", true);
	}
	@Component
	private Zone myZone;
	
	@Persist 
    private Integer count;

    @Inject
    private Request request;

    @Property
    private String goalName;
    
    @OnEvent(EventConstants.ACTIVATE)
    void init()
    {
        if (count == null)
            count = 0;
    }

    public Integer getCount()
    {
        return count++;
    }

    @OnEvent(EventConstants.ACTION)
    Object updateCount()
    {
        if (!request.isXHR()) { return this; }
        return myZone;
    }