

public class PatientInfoBean_K {
	
	private String chart_num;
	private String name;
	private String gender;
	private int age;
	
	
	private int babyTooth; //유치 개수
	private int permanentTooth; //영구치 개수
	private int losePermanentTooth_front; //앞니 상실치아 개수
	private int losePermanentTooth_back; //어금니 상실치아 개수
	private int implant; //인공치아 갯수
	private int dentures; //틀니 갯수
	private int leaving; //우식방치치아 개수
	private int treatment; //충전치아 개수
	private int sulcus; //깊은 열구 치아 개수
	
	private String plaque_score; //치면세균막 지수
	private String tartar; //치석
	private String gingivitis; //치은염
	private String microscope_sal_amount; //살사균 양
	private String microscope_sal_movement; //살사균 활동성
	private String microscope_gu_amount; //구균 양
	private String microscope_gu_movement; //구균 활동성
	private String snyder; //스나이더검사
	
	private String malocclusion; //부정교합
	private String odontoclasis; //치아파절,외상
	private String infection; //구강감염
	private String bad_breath; //구취
	private String brushMethod; //이닦기 방법
	
	private String periodontal; //치주낭
	private String mobility; //치아동요
	private String mandibular; //약관절 이상
	private String aesthetic; //심미요인
	
	private String saliva; //타액 양
	private String consistency; //점조도
	private String wisdomTooth_pain; //사랑니 통증
	private String dazzling; //시린니
	private String partialDenture; //국소의치 장착여부
	private String prosthesis_need; //보철물,의치 필요
	private String denture_need; //틀니 필요도
	
	private String sugar_frequency; //당섭취
	private String brush_num; //이닦은 횟수
	private String chew_food; //치아저작 기능
	private String brush_time; //이닦기 시기
	private String disease_num; //전신질환 개수
	private String disease_kind; //전신질환 종류
	private String prevention_visit; //예방진료 여부
	private String visit; //치과방문 경험
	private String scaling; //스케일링 경험
	private String care_product; //구강용품
	private String drinking; //음주여부
	private String smoking; //흡연여부
	private String drink_and_smoke; //음주&흡연
	private String xerostomia; //구강건조증
	private String learn; //보건교욱 여부
	private String pregnancy; //임신여부
	
	
	public String getChart_num() {
		return chart_num;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public int getAge() {
		return age;
	}
	public int getBabyTooth() {
		return babyTooth;
	}
	public int getPermanentTooth() {
		return permanentTooth;
	}
	public int getLosePermanentTooth_front() {
		return losePermanentTooth_front;
	}
	public int getLosePermanentTooth_back() {
		return losePermanentTooth_back;
	}
	public int getImplant() {
		return implant;
	}
	public int getDentures() {
		return dentures;
	}
	public int getLeaving() {
		return leaving;
	}
	public int getTreatment() {
		return treatment;
	}
	public int getSulcus() {
		return sulcus;
	}
	public String getPlaque_score() {
		return plaque_score;
	}
	public String getTartar() {
		return tartar;
	}
	public String getGingivitis() {
		return gingivitis;
	}
	public String getMicroscope_sal_amount() {
		return microscope_sal_amount;
	}
	public String getMicroscope_sal_movement() {
		return microscope_sal_movement;
	}
	public String getMicroscope_gu_amount() {
		return microscope_gu_amount;
	}
	public String getMicroscope_gu_movement() {
		return microscope_gu_movement;
	}
	public String getSnyder() {
		return snyder;
	}
	public String getMalocclusion() {
		return malocclusion;
	}
	public String getOdontoclasis() {
		return odontoclasis;
	}
	public String getInfection() {
		return infection;
	}
	public String getBad_breath() {
		return bad_breath;
	}
	public String getBrushMethod() {
		return brushMethod;
	}
	public String getPeriodontal() {
		return periodontal;
	}
	public String getMobility() {
		return mobility;
	}
	public String getMandibular() {
		return mandibular;
	}
	public String getAesthetic() {
		return aesthetic;
	}
	public String getSaliva() {
		return saliva;
	}
	public String getConsistency() {
		return consistency;
	}
	public String getWisdomTooth_pain() {
		return wisdomTooth_pain;
	}
	public String getDazzling() {
		return dazzling;
	}
	public String getPartialDenture() {
		return partialDenture;
	}
	public String getProsthesis_need() {
		return prosthesis_need;
	}
	public String getDenture_need() {
		return denture_need;
	}
	public String getSugar_frequency() {
		return sugar_frequency;
	}
	public String getBrush_num() {
		return brush_num;
	}
	public String getChew_food() {
		return chew_food;
	}
	public String getBrush_time() {
		return brush_time;
	}
	public String getDisease_num() {
		return disease_num;
	}
	public String getDisease_kind() {
		return disease_kind;
	}
	public String getPrevention_visit() {
		return prevention_visit;
	}
	public String getVisit() {
		return visit;
	}
	public String getScaling() {
		return scaling;
	}
	public String getCare_product() {
		return care_product;
	}
	public String getDrinking() {
		return drinking;
	}
	public String getSmoking() {
		return smoking;
	}
	public String getDrink_and_smoke() {
		return drink_and_smoke;
	}
	public String getXerostomia() {
		return xerostomia;
	}
	public String getLearn() {
		return learn;
	}
	public String getPregnancy() {
		return pregnancy;
	}
	
	public void setChart_num(String chart_num) {
		this.chart_num = chart_num;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setBabyTooth(int babyTooth) {
		this.babyTooth = babyTooth;
	}
	public void setPermanentTooth(int permanentTooth) {
		this.permanentTooth = permanentTooth;
	}
	public void setLosePermanentTooth_front(int losePermanentTooth_front) {
		this.losePermanentTooth_front = losePermanentTooth_front;
	}
	public void setLosePermanentTooth_back(int losePermanentTooth_back) {
		this.losePermanentTooth_back = losePermanentTooth_back;
	}
	public void setImplant(int implant) {
		this.implant = implant;
	}
	public void setDentures(int dentures) {
		this.dentures = dentures;
	}
	public void setLeaving(int leaving) {
		this.leaving = leaving;
	}
	public void setTreatment(int treatment) {
		this.treatment = treatment;
	}
	public void setSulcus(int sulcus) {
		this.sulcus = sulcus;
	}
	public void setPlaque_score(String plaque_score) {
		this.plaque_score = plaque_score;
	}
	public void setTartar(String tartar) {
		this.tartar = tartar;
	}
	public void setGingivitis(String gingivitis) {
		this.gingivitis = gingivitis;
	}
	public void setMicroscope_sal_amount(String microscope_sal_amount) {
		this.microscope_sal_amount = microscope_sal_amount;
	}
	public void setMicroscope_sal_movement(String microscope_sal_movement) {
		this.microscope_sal_movement = microscope_sal_movement;
	}
	public void setMicroscope_gu_amount(String microscope_gu_amount) {
		this.microscope_gu_amount = microscope_gu_amount;
	}
	public void setMicroscope_gu_movement(String microscope_gu_movement) {
		this.microscope_gu_movement = microscope_gu_movement;
	}
	public void setSnyder(String snyder) {
		this.snyder = snyder;
	}
	public void setMalocclusion(String malocclusion) {
		this.malocclusion = malocclusion;
	}
	public void setOdontoclasis(String odontoclasis) {
		this.odontoclasis = odontoclasis;
	}
	public void setInfection(String infection) {
		this.infection = infection;
	}
	public void setBad_breath(String bad_breath) {
		this.bad_breath = bad_breath;
	}
	public void setBrushMethod(String brushMethod) {
		this.brushMethod = brushMethod;
	}
	public void setPeriodontal(String periodontal) {
		this.periodontal = periodontal;
	}
	public void setMobility(String mobility) {
		this.mobility = mobility;
	}
	public void setMandibular(String mandibular) {
		this.mandibular = mandibular;
	}
	public void setAesthetic(String aesthetic) {
		this.aesthetic = aesthetic;
	}
	public void setSaliva(String saliva) {
		this.saliva = saliva;
	}
	public void setConsistency(String consistency) {
		this.consistency = consistency;
	}
	public void setWisdomTooth_pain(String wisdomTooth_pain) {
		this.wisdomTooth_pain = wisdomTooth_pain;
	}
	public void setDazzling(String dazzling) {
		this.dazzling = dazzling;
	}
	public void setPartialDenture(String partialDenture) {
		this.partialDenture = partialDenture;
	}
	public void setProsthesis_need(String prosthesis_need) {
		this.prosthesis_need = prosthesis_need;
	}
	public void setDenture_need(String denture_need) {
		this.denture_need = denture_need;
	}
	public void setSugar_frequency(String sugar_frequency) {
		this.sugar_frequency = sugar_frequency;
	}
	public void setBrush_num(String brush_num) {
		this.brush_num = brush_num;
	}
	public void setChew_food(String chew_food) {
		this.chew_food = chew_food;
	}
	public void setBrush_time(String brush_time) {
		this.brush_time = brush_time;
	}
	public void setDisease_num(String disease_num) {
		this.disease_num = disease_num;
	}
	public void setDisease_kind(String disease_kind) {
		this.disease_kind = disease_kind;
	}
	public void setPrevention_visit(String prevention_visit) {
		this.prevention_visit = prevention_visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
	public void setScaling(String scaling) {
		this.scaling = scaling;
	}
	public void setCare_product(String care_product) {
		this.care_product = care_product;
	}
	public void setDrinking(String drinking) {
		this.drinking = drinking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public void setDrink_and_smoke(String drink_and_smoke) {
		this.drink_and_smoke = drink_and_smoke;
	}
	public void setXerostomia(String xerostomia) {
		this.xerostomia = xerostomia;
	}
	public void setLearn(String learn) {
		this.learn = learn;
	}
	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}
	
}
