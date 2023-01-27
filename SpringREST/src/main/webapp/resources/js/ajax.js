

//vs코드 말고 여기서 ajax.js 파일 마우스 우클릭 - open with - jsEditor로 열어도 됨

const btn1 = document.getElementById('btn-xhttp');
const out = document.getElementById('out');


//실습 1. AJAX 요청 만들고 보내기~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//1. ajax를 위한 xhttp인스턴스를 생성
const xhttp = new XMLHttpRequest();

//2. xhttp 의 이벤트 설정
xhttp.addEventListener('readystatechange', (e)=> {
  
    console.log('readystate event : ', e); //이벤트가 발생할 때마다 찍힘 

    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 1 ) {
        console.log('서버로 비동기 요청을 보냈다.');
    } else if(readyState == 2 ) {
        console.log('서버가 내 요청을 받았다.');
    } else if(readyState == 3 ) {
        console.log('서버가 내 요청을 처리하기 시작했다.');
    } else if(readyState == 4 ) {
        console.log('서버의 처리가 끝나고 내 요청에 대한 응답이 도착했다.');
        console.log('응답은 response나 responseText에 들어 있다.');

        //5. JSON 문자열 -> Javascript Object로 변환 
        const pizza = JSON.parse(responseText);
        console.log('pizza name : ', pizza.p_name);
        console.log('pizza calories : ', pizza.p_calories);
        
        const pizzaDiv = document.createElement('div');
        out.appendChild(pizzaDiv);
        pizzaDiv.innerText = `${pizza.p_name}/${pizza.p_calories}/${pizza.p_price}`;     

    } else {
        console.log('?? : ', readyState, ',', responseText);
    } 
});

btn1.addEventListener('click', (e)=> {
    //3.  새로운 xhttp 연결을 설정 open(method, url)
    xhttp.open('GET', '/restful/sample/pizza/id/22');
    //4. 원하는 타이밍에 요청을 전송. 지금은 버튼을 눌렀을 때 요청을 보냄
    xhttp.send();
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//실습2. ajax로 update(PUT) 요청 보내기 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

const btn2 = document.getElementById('btn-xhttp-put');
const select1 = document.getElementById('select-pizza-name');

const input_pizza_name = document.getElementById('input-pizza-name');
const input_pizza_price = document.getElementById('input-pizza-price');
const input_pizza_calories = document.getElementById('input-pizza-calories');
const input_pizza_id = document.getElementById('input-pizza-id');

const out2 = document.getElementById('out2');


//실습2_요청1. 피자 이름 선택 시 인풋 박스에 내용이 채워지도록 선택된 이름에 해당하는 피자의 데이터를 요청

const xhttp2 = new XMLHttpRequest();
xhttp2.addEventListener('readystatechange', (e)=> {
	const readyState = e.target.readyState;
		
	if(readyState == 4) {
		const responseText = e.target.responseText;

		const pizza = JSON.parse(responseText);

		input_pizza_calories.value = pizza.p_calories;
		input_pizza_price.value = pizza.p_price;
		input_pizza_id.value = pizza.p_id; 
		input_pizza_name.value = pizza.p_name;

	}
});

select1.addEventListener('change', (e)=> {
	console.log(e);
	console.log('selected option : ', e.target.value); //선택된 피자의 id값 반환
	xhttp2.open('GET', '/restful/sample/pizza/id/' + e.target.value); 
	xhttp2.send();
}); 



//실습2_요청2 input_pizza_id 에 저장된 p_id 

const xhttp3 = new XMLHttpRequest();
xhttp3.addEventListener('readystatechange', (e) => {
	const readyState = e.target.readyState;

	if(readyState == 4) {
		const responseText = e.target.responseText;
		if(responseText == 1) {
		out2.innerText = 'SUCCESS';
		out2.style.color = 'green';
		} else {
			out2.innerText = 'FAILED';
			out2.style.color = 'red';
		}
	} 
});

btn2.addEventListener('click', (e)=> {
	
	//btn2 클릭했을 때 선택된피자의 값들 뽑아보기
	//console.log('input pizza id: ' , input_pizza_id.value);
	//console.log('input pizza name: ' , input_pizza_name.value);
	//console.log('input pizza price: ' , input_pizza_price.value);
	//console.log('input pizza calories: ' , input_pizza_calories.value);


	//GET방식은 주소 뒤에 ?name=value&... 로 실어 보내면 되지만 그 외의 방식은 send() 메서드에 JSON방식의 문자열로 (중괄호랑 "속성" 그거) 데이터를 실어 보낼 수 있다★
	//근데 JSON방식을 직접 치면 얼마나 귀찮음 xhttp3.send(`{'name' : ${input_pizza_id.value}, 이런 식으로 귀찮게 언제 다 적음 }`);
	xhttp3.open('PUT', '/restful/sample/pizza/update');	


	//이렇게 선언해준 담에 아래의 stringify메서드로 변환해주면 됨.  JSON.stringify는 JSON.parse의 반대. JSON.parse()는 JSON으로 온 걸 object로 변환해주는 거였음  
	const pizza = {
		p_id: 		input_pizza_id.value, 
		p_name:		input_pizza_name.value,
		p_price:	input_pizza_price.value, 
		p_calories: input_pizza_calories.value
	}
	

	// xhr 요청헤더 설정 (JSON형식으로 보낸다고 서버에 알려야 한다.)
	
	xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	
	//이제 Content-Type: text/html;charset=utf-8 에서 Content-Type: application/json;charset=UTF-8로 바뀜
	
	
	// object -> JSON (stringify)
	
	
	xhttp3.send(JSON.stringify(pizza));
	
	
	//이제 PizzaRestController에서 Put매핑으로된 updatePizza() 로 받아보자 
	//근데 여기서 안 되네 개발자도구창에서 네트워크 - ㅁpizza 눌러보면 Content-Type이 Content-Type: text/html;charset=utf-8라고 돼 있음
	//위로 가서 xhr요청헤더 설정해주기	
	
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//실습3. ajax로 insert(POST) 요청 보내기 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
const btn3 = document.getElementById('btn-xhttp-post');
const input_pizza_name2 = document.getElementById('post-pizza-name');
const input_pizza_price2 = document.getElementById('post-pizza-price');
const input_pizza_calories2 = document.getElementById('post-pizza-calories');

const out3 = document.getElementById('out3');

const xhttp4 = new XMLHttpRequest();
xhttp4.addEventListener('readystatechange', (e)=> {
	const readyState = e.target.readyState;
	
	if(readyState == 4) {
		
		// e.target.status : 컨트롤러에서 만들어 보낸 http 상태 코드가 들어 있다.
		const httpStatus = e.target.status;
		
		console.log('xhr4 http status : ', httpStatus);
		
		if (httpStatus == 200) {
			out3.style.color = 'green';
			out3.innerText = '200 ok!';
		} else if (httpStatus == 500) {
			out3.style.color = 'red';
			out3.innerText = '500 internal server error!';
		} else if (httpStatus == 400) {
			out3.style.color = 'orange';
			out3.innerText = 'null value detected!';
		}
	}
});



btn3.addEventListener('click', (e)=> {

	xhttp4.open('POST', '/restful/sample/pizza/insert');
	const pizza = {
		p_name: input_pizza_name2.value, 
		p_price: input_pizza_price2.value, 
		p_calories: input_pizza_calories2.value
	}

	xhttp4.setRequestHeader('Content-Type', 'application/json;characterset=UTF-8');
	xhttp4.send(JSON.stringify(pizza));
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

