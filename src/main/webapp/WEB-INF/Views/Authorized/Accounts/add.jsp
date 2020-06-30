<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <form method="POST" action="" class="form-horizontal" onsubmit="return onSubmit()">
      <div class="row">
        <div class="col-md-12">
          <div class="card ">
            <div class="card-header card-header-rose card-header-text">
              <div class="card-text">
                <h4 class="card-title">Agregar Cuenta</h4>
              </div>
            </div>
            <div class="card-body ">
              <ul class="nav nav-pills nav-pills-rose nav-pills-icons justify-content-center" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active show" data-toggle="tab" href="#base-info" role="tablist">
                    <i class="material-icons">settings</i>
                    <span class="notification pulse" id="base-info-error" style="
                    display: none;
                    position: absolute;
                    top: 4px;
                    border: 1px solid #fff;
                    font-size: 9px;
                    background: #f44336;
                    color: #fff;
                    min-width: 20px;
                    height: 20px;
                    border-radius: 10px;
                    line-height: 19px;">
                      !
                    </span>
                    Información básica
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#customer-data" role="tablist">
                    <i class="material-icons">person</i>
                    <span class="notification pulse" id="customer-error" style="
                    display: none;
                    position: absolute;
                    top: 4px;
                    border: 1px solid #fff;
                    font-size: 9px;
                    background: #f44336;
                    color: #fff;
                    min-width: 20px;
                    height: 20px;
                    border-radius: 10px;
                    line-height: 19px;">
                      !
                    </span>
                    Cliente
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#account-type" role="tablist">
                    <i class="material-icons">work</i>
                    <span class="notification pulse" id="account-type-error" style="
                    display: none;
                    position: absolute;
                    top: 4px;
                    border: 1px solid #fff;
                    font-size: 9px;
                    background: #f44336;
                    color: #fff;
                    min-width: 20px;
                    height: 20px;
                    border-radius: 10px;
                    line-height: 19px;">
                      !
                    </span>
                    Tipo de cuenta
                  </a>
                </li>
              </ul>
              <div class="tab-content tab-space tab-subcategories">
                <div class="tab-pane active show" id="base-info">
                  <div class="row">
                    <label class="col-sm-2 col-form-label">Número de cuenta</label>
                    <div class="col-sm-10">
                      <div class="form-group">
                        <input type="number" name="accountNumber" class="form-control" placeholder="Ingrese número de cuenta">
                        <button type="button" class="btn btn-warning btn-round btn-fab btn-sm" style="position: absolute;top: -5px;right: 0;" onclick="setRandomAccountNumber()">
                          <i class="material-icons">sync</i>
                        </button>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <label class="col-sm-2 col-form-label">Alias</label>
                    <div class="col-sm-10">
                      <div class="form-group">
                        <input type="text" name="alias" class="form-control" placeholder="Ingrese el alias del usuario">
                        <button type="button" class="btn btn-warning btn-round btn-fab btn-sm" style="position: absolute;top: -5px;right: 0;" onclick="setRandomAlias()">
                          <i class="material-icons">sync</i>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="customer-data">
                  <div class="material-datatables">
                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                      <thead>
                      <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>E-Mail</th>
                        <th>Usuario</th>
                        <th>Monto máximo para préstamo</th>
                        <th></th>
                      </tr>
                      </thead>
                      <tfoot>
                      <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>E-Mail</th>
                        <th>Usuario</th>
                        <th>Monto máximo para préstamo</th>
                        <th></th>
                      </tr>
                      </tfoot>
                      <tbody>
                      <c:forEach var="customer" items="${ customers }">
                        <tr id="customer-${ customer.id }">
                          <td>${ customer.name }</td>
                          <td>${ customer.lastName }</td>
                          <td>${ customer.dni }</td>
                          <td>${ customer.email }</td>
                          <td>${ customer.userName }</td>
                          <td>${ customer.maxLoanAmount }</td>
                          <td>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="customer" value="${ customer.id }">
                                <span class="circle">
                              <span class="check"></span>
                            </span>
                              </label>
                            </div>
                          </td>
                        </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                <div class="tab-pane" id="account-type">
                  <div class="material-datatables">
                    <table id="datatables2" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                      <thead>
                      <tr>
                        <th>Tipo de cuenta</th>
                        <th>Moneda</th>
                        <th></th>
                      </tr>
                      </thead>
                      <tfoot>
                      <tr>
                        <th>Tipo de cuenta</th>
                        <th>Moneda</th>
                        <th></th>
                      </tr>
                      </tfoot>
                      <tbody>
                      <c:forEach var="accountType" items="${ accountTypes }">
                        <tr id="customer-${ accountType.id }">
                          <td>${ accountType.name }</td>
                          <td>${ accountType.currencyType }</td>
                          <td>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="accountType" value="${ accountType.id }">
                                <span class="circle">
                              <span class="check"></span>
                            </span>
                              </label>
                            </div>
                          </td>
                        </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <div class="card-footer" style="margin: 0 0px 10px;">
              <div class="row" style="width: 100%">
                <div class="col-2" style="padding-left: 30px;">
                  <a href="${request.getContextPath()}/UTN_LABV_TP_Integrador/accounts" class="btn btn-fill btn-dark">Cancelar</a>
                </div>
                <div class="offset-6 col-2 offset-sm-8 col-sm-2" style="padding-right: 5px;">
                  <button type="submit" class="btn btn-fill btn-rose" style="float: right;">Agregar cuenta</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </jsp:body>
</layout:authorized>
<script>
  const names = ["abandoned","able","absolute","adorable","adventurous","academic","acceptable","acclaimed","accomplished","accurate","aching","acidic","acrobatic","active","actual","adept","admirable","admired","adolescent","adorable","adored","advanced","afraid","affectionate","aged","aggravating","aggressive","agile","agitated","agonizing","agreeable","ajar","alarmed","alarming","alert","alienated","alive","all","altruistic","amazing","ambitious","ample","amused","amusing","anchored","ancient","angelic","angry","anguished","animated","annual","another","antique","anxious","any","apprehensive","appropriate","apt","arctic","arid","aromatic","artistic","ashamed","assured","astonishing","athletic","attached","attentive","attractive","austere","authentic","authorized","automatic","avaricious","average","aware","awesome","awful","awkward","babyish","bad","back","baggy","bare","barren","basic","beautiful","belated","beloved","beneficial","better","best","bewitched","big","big-hearted","biodegradable","bite-sized","bitter","black","black-and-white","bland","blank","blaring","bleak","blind","blissful","blond","blue","blushing","bogus","boiling","bold","bony","boring","bossy","both","bouncy","bountiful","bowed","brave","breakable","brief","bright","brilliant","brisk","broken","bronze","brown","bruised","bubbly","bulky","bumpy","buoyant","burdensome","burly","bustling","busy","buttery","buzzing","calculating","calm","candid","canine","capital","carefree","careful","careless","caring","cautious","cavernous","celebrated","charming","cheap","cheerful","cheery","chief","chilly","chubby","circular","classic","clean","clear","clear-cut","clever","close","closed","cloudy","clueless","clumsy","cluttered","coarse","cold","colorful","colorless","colossal","comfortable","common","compassionate","competent","complete","complex","complicated","composed","concerned","concrete","confused","conscious","considerate","constant","content","conventional","cooked","cool","cooperative","coordinated","corny","corrupt","costly","courageous","courteous","crafty","crazy","creamy","creative","creepy","criminal","crisp","critical","crooked","crowded","cruel","crushing","cuddly","cultivated","cultured","cumbersome","curly","curvy","cute","cylindrical","damaged","damp","dangerous","dapper","daring","darling","dark","dazzling","dead","deadly","deafening","dear","dearest","decent","decimal","decisive","deep","defenseless","defensive","defiant","deficient","definite","definitive","delayed","delectable","delicious","delightful","delirious","demanding","dense","dental","dependable","dependent","descriptive","deserted","detailed","determined","devoted","different","difficult","digital","diligent","dim","dimpled","dimwitted","direct","disastrous","discrete","disfigured","disgusting","disloyal","dismal","distant","downright","dreary","dirty","disguised","dishonest","dismal","distant","distinct","distorted","dizzy","dopey","doting","double","downright","drab","drafty","dramatic","dreary","droopy","dry","dual","dull","dutiful","each","eager","earnest","early","easy","easy-going","ecstatic","edible","educated","elaborate","elastic","elated","elderly","electric","elegant","elementary","elliptical","embarrassed","embellished","eminent","emotional","empty","enchanted","enchanting","energetic","enlightened","enormous","enraged","entire","envious","equal","equatorial","essential","esteemed","ethical","euphoric","even","evergreen","everlasting","every","evil","exalted","excellent","exemplary","exhausted","excitable","excited","exciting","exotic","expensive","experienced","expert","extraneous","extroverted","extra-large","extra-small","fabulous","failing","faint","fair","faithful","fake","false","familiar","famous","fancy","fantastic","far","faraway","far-flung","far-off","fast","fat","fatal","fatherly","favorable","favorite","fearful","fearless","feisty","feline","female","feminine","few","fickle","filthy","fine","finished","firm","first","firsthand","fitting","fixed","flaky","flamboyant","flashy","flat","flawed","flawless","flickering","flimsy","flippant","flowery","fluffy","fluid","flustered","focused","fond","foolhardy","foolish","forceful","forked","formal","forsaken","forthright","fortunate","fragrant","frail","frank","frayed","free","French","fresh","frequent","friendly","frightened","frightening","frigid","frilly","frizzy","frivolous","front","frosty","frozen","frugal","fruitful","full","fumbling","functional","funny","fussy","fuzzy","gargantuan","gaseous","general","generous","gentle","genuine","giant","giddy","gigantic","gifted","giving","glamorous","glaring","glass","gleaming","gleeful","glistening","glittering","gloomy","glorious","glossy","glum","golden","good","good-natured","gorgeous","graceful","gracious","grand","grandiose","granular","grateful","grave","gray","great","greedy","green","gregarious","grim","grimy","gripping","grizzled","gross","grotesque","grouchy","grounded","growing","growling","grown","grubby","gruesome","grumpy","guilty","gullible","gummy","hairy","half","handmade","handsome","handy","happy","happy-go-lucky","hard","hard-to-find","harmful","harmless","harmonious","harsh","hasty","hateful","haunting","healthy","heartfelt","hearty","heavenly","heavy","hefty","helpful","helpless","hidden","hideous","high","high-level","hilarious","hoarse","hollow","homely","honest","honorable","honored","hopeful","horrible","hospitable","hot","huge","humble","humiliating","humming","humongous","hungry","hurtful","husky","icky","icy","ideal","idealistic","identical","idle","idiotic","idolized","ignorant","ill","illegal","ill-fated","ill-informed","illiterate","illustrious","imaginary","imaginative","immaculate","immaterial","immediate","immense","impassioned","impeccable","impartial","imperfect","imperturbable","impish","impolite","important","impossible","impractical","impressionable","impressive","improbable","impure","inborn","incomparable","incompatible","incomplete","inconsequential","incredible","indelible","inexperienced","indolent","infamous","infantile","infatuated","inferior","infinite","informal","innocent","insecure","insidious","insignificant","insistent","instructive","insubstantial","intelligent","intent","intentional","interesting","internal","international","intrepid","ironclad","irresponsible","irritating","itchy","jaded","jagged","jam-packed","jaunty","jealous","jittery","joint","jolly","jovial","joyful","joyous","jubilant","judicious","juicy","jumbo","junior","jumpy","juvenile","kaleidoscopic","keen","key","kind","kindhearted","kindly","klutzy","knobby","knotty","knowledgeable","knowing","known","kooky","kosher","lame","lanky","large","last","lasting","late","lavish","lawful","lazy","leading","lean","leafy","left","legal","legitimate","light","lighthearted","likable","likely","limited","limp","limping","linear","lined","liquid","little","live","lively","livid","loathsome","lone","lonely","long","long-term","loose","lopsided","lost","loud","lovable","lovely","loving","low","loyal","lucky","lumbering","luminous","lumpy","lustrous","luxurious","mad","made-up","magnificent","majestic","major","male","mammoth","married","marvelous","masculine","massive","mature","meager","mealy","mean","measly","meaty","medical","mediocre","medium","meek","mellow","melodic","memorable","menacing","merry","messy","metallic","mild","milky","mindless","miniature","minor","minty","miserable","miserly","misguided","misty","mixed","modern","modest","moist","monstrous","monthly","monumental","moral","mortified","motherly","motionless","mountainous","muddy","muffled","multicolored","mundane","murky","mushy","musty","muted","mysterious","naive","narrow","nasty","natural","naughty","nautical","near","neat","necessary","needy","negative","neglected","negligible","neighboring","nervous","new","next","nice","nifty","nimble","nippy","nocturnal","noisy","nonstop","normal","notable","noted","noteworthy","novel","noxious","numb","nutritious","nutty","obedient","obese","oblong","oily","oblong","obvious","occasional","odd","oddball","offbeat","offensive","official","old","old-fashioned","only","open","optimal","optimistic","opulent","orange","orderly","organic","ornate","ornery","ordinary","original","other","our","outlying","outgoing","outlandish","outrageous","outstanding","oval","overcooked","overdue","overjoyed","overlooked","palatable","pale","paltry","parallel","parched","partial","passionate","past","pastel","peaceful","peppery","perfect","perfumed","periodic","perky","personal","pertinent","pesky","pessimistic","petty","phony","physical","piercing","pink","pitiful","plain","plaintive","plastic","playful","pleasant","pleased","pleasing","plump","plush","polished","polite","political","pointed","pointless","poised","poor","popular","portly","posh","positive","possible","potable","powerful","powerless","practical","precious","present","prestigious","pretty","precious","previous","pricey","prickly","primary","prime","pristine","private","prize","probable","productive","profitable","profuse","proper","proud","prudent","punctual","pungent","puny","pure","purple","pushy","putrid","puzzled","puzzling","quaint","qualified","quarrelsome","quarterly","queasy","querulous","questionable","quick","quick-witted","quiet","quintessential","quirky","quixotic","quizzical","radiant","ragged","rapid","rare","rash","raw","recent","reckless","rectangular","ready","real","realistic","reasonable","red","reflecting","regal","regular","reliable","relieved","remarkable","remorseful","remote","repentant","required","respectful","responsible","repulsive","revolving","rewarding","rich","rigid","right","ringed","ripe","roasted","robust","rosy","rotating","rotten","rough","round","rowdy","royal","rubbery","rundown","ruddy","rude","runny","rural","rusty","sad","safe","salty","same","sandy","sane","sarcastic","sardonic","satisfied","scaly","scarce","scared","scary","scented","scholarly","scientific","scornful","scratchy","scrawny","second","secondary","second-hand","secret","self-assured","self-reliant","selfish","sentimental","separate","serene","serious","serpentine","several","severe","shabby","shadowy","shady","shallow","shameful","shameless","sharp","shimmering","shiny","shocked","shocking","shoddy","short","short-term","showy","shrill","shy","sick","silent","silky","silly","silver","similar","simple","simplistic","sinful","single","sizzling","skeletal","skinny","sleepy","slight","slim","slimy","slippery","slow","slushy","small","smart","smoggy","smooth","smug","snappy","snarling","sneaky","sniveling","snoopy","sociable","soft","soggy","solid","somber","some","spherical","sophisticated","sore","sorrowful","soulful","soupy","sour","Spanish","sparkling","sparse","specific","spectacular","speedy","spicy","spiffy","spirited","spiteful","splendid","spotless","spotted","spry","square","squeaky","squiggly","stable","staid","stained","stale","standard","starchy","stark","starry","steep","sticky","stiff","stimulating","stingy","stormy","straight","strange","steel","strict","strident","striking","striped","strong","studious","stunning","stupendous","stupid","sturdy","stylish","subdued","submissive","substantial","subtle","suburban","sudden","sugary","sunny","super","superb","superficial","superior","supportive","sure-footed","surprised","suspicious","svelte","sweaty","sweet","sweltering","swift","sympathetic","tall","talkative","tame","tan","tangible","tart","tasty","tattered","taut","tedious","teeming","tempting","tender","tense","tepid","terrible","terrific","testy","thankful","that","these","thick","thin","third","thirsty","this","thorough","thorny","those","thoughtful","threadbare","thrifty","thunderous","tidy","tight","timely","tinted","tiny","tired","torn","total","tough","traumatic","treasured","tremendous","tragic","trained","tremendous","triangular","tricky","trifling","trim","trivial","troubled","true","trusting","trustworthy","trusty","truthful","tubby","turbulent","twin","ugly","ultimate","unacceptable","unaware","uncomfortable","uncommon","unconscious","understated","unequaled","uneven","unfinished","unfit","unfolded","unfortunate","unhappy","unhealthy","uniform","unimportant","unique","united","unkempt","unknown","unlawful","unlined","unlucky","unnatural","unpleasant","unrealistic","unripe","unruly","unselfish","unsightly","unsteady","unsung","untidy","untimely","untried","untrue","unused","unusual","unwelcome","unwieldy","unwilling","unwitting","unwritten","upbeat","upright","upset","urban","usable","used","useful","useless","utilized","utter","vacant","vague","vain","valid","valuable","vapid","variable","vast","velvety","venerated","vengeful","verifiable","vibrant","vicious","victorious","vigilant","vigorous","villainous","violet","violent","virtual","virtuous","visible","vital","vivacious","vivid","voluminous","wan","warlike","warm","warmhearted","warped","wary","wasteful","watchful","waterlogged","watery","wavy","wealthy","weak","weary","webbed","wee","weekly","weepy","weighty","weird","welcome","well-documented","well-groomed","well-informed","well-lit","well-made","well-off","well-to-do","well-worn","wet","which","whimsical","whirlwind","whispered","white","whole","whopping","wicked","wide","wide-eyed","wiggly","wild","willing","wilted","winding","windy","winged","wiry","wise","witty","wobbly","woeful","wonderful","wooden","woozy","wordy","worldly","worn","worried","worrisome","worse","worst","worthless","worthwhile","worthy","wrathful","wretched","writhing","wrong","wry","yawning","yearly","yellow","yellowish","young","youthful","yummy","zany","zealous","zesty","zigzag","rocky"];

  const getRandomAlias = () => {
    const alias = [];

    new Array(3).fill(null).map(() => alias.push(names[getRandomNumber(names.length)]));

    return alias.join(".").toUpperCase();
  }

  const getRandomNumber = (maxValue = 1000000000) => Math.floor(Math.random() * maxValue);

  function onSubmit() {
    const validations = [];
    validations.push(document.getElementsByName("accountNumber")[0].value !== "");
    validations.push(document.getElementsByName("alias")[0].value !== "");
    validations.push(Array.from(document.getElementsByName("customer")).some(input => input.checked));
    validations.push(Array.from(document.getElementsByName("accountType")).some(input => input.checked));

    const [accountNumberValidated, aliasValidated, customerValidated, accountTypeValidated] = validations;

    toggleError(accountNumberValidated && aliasValidated, "base-info-error");
    toggleError(customerValidated, "customer-error");
    toggleError(accountTypeValidated, "account-type-error");

    return ! validations.some(validation => ! validation); // Si alguna validación es falsa, no puede submitear.
  }

  const toggleError = (isValid, id) => {
    const selector = $("#" + id);

    isValid ? selector.hide("slow") : selector.fadeIn("slow");
  }

  const setRandomAccountNumber = () => $('[name="accountNumber"]').val(getRandomNumber());
  const setRandomAlias = () => $('[name="alias"]').val(getRandomAlias());

  setRandomAccountNumber();
  setRandomAlias();
</script>