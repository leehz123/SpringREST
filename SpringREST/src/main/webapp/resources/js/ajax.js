

//vs�ڵ� ���� ���⼭ ajax.js ���� ���콺 ��Ŭ�� - open with - jsEditor�� ��� ��

const btn1 = document.getElementById('btn-xhttp');
const out = document.getElementById('out');


//�ǽ� 1. AJAX ��û ����� ������~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//1. ajax�� ���� xhttp�ν��Ͻ��� ����
const xhttp = new XMLHttpRequest();

//2. xhttp �� �̺�Ʈ ����
xhttp.addEventListener('readystatechange', (e)=> {
  
    console.log('readystate event : ', e); //�̺�Ʈ�� �߻��� ������ ���� 

    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 1 ) {
        console.log('������ �񵿱� ��û�� ���´�.');
    } else if(readyState == 2 ) {
        console.log('������ �� ��û�� �޾Ҵ�.');
    } else if(readyState == 3 ) {
        console.log('������ �� ��û�� ó���ϱ� �����ߴ�.');
    } else if(readyState == 4 ) {
        console.log('������ ó���� ������ �� ��û�� ���� ������ �����ߴ�.');
        console.log('������ response�� responseText�� ��� �ִ�.');

        //5. JSON ���ڿ� -> Javascript Object�� ��ȯ 
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
    //3.  ���ο� xhttp ������ ���� open(method, url)
    xhttp.open('GET', '/restful/sample/pizza/id/22');
    //4. ���ϴ� Ÿ�ֿ̹� ��û�� ����. ������ ��ư�� ������ �� ��û�� ����
    xhttp.send();
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//�ǽ�2. ajax�� update(PUT) ��û ������ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

const btn2 = document.getElementById('btn-xhttp-put');
const select1 = document.getElementById('select-pizza-name');

const input_pizza_name = document.getElementById('input-pizza-name');
const input_pizza_price = document.getElementById('input-pizza-price');
const input_pizza_calories = document.getElementById('input-pizza-calories');
const input_pizza_id = document.getElementById('input-pizza-id');

const out2 = document.getElementById('out2');


//�ǽ�2_��û1. ���� �̸� ���� �� ��ǲ �ڽ��� ������ ä�������� ���õ� �̸��� �ش��ϴ� ������ �����͸� ��û

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
	console.log('selected option : ', e.target.value); //���õ� ������ id�� ��ȯ
	xhttp2.open('GET', '/restful/sample/pizza/id/' + e.target.value); 
	xhttp2.send();
}); 



//�ǽ�2_��û2 input_pizza_id �� ����� p_id 

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
	
	//btn2 Ŭ������ �� ���õ������� ���� �̾ƺ���
	//console.log('input pizza id: ' , input_pizza_id.value);
	//console.log('input pizza name: ' , input_pizza_name.value);
	//console.log('input pizza price: ' , input_pizza_price.value);
	//console.log('input pizza calories: ' , input_pizza_calories.value);


	//GET����� �ּ� �ڿ� ?name=value&... �� �Ǿ� ������ ������ �� ���� ����� send() �޼��忡 JSON����� ���ڿ��� (�߰�ȣ�� "�Ӽ�" �װ�) �����͸� �Ǿ� ���� �� �ִ١�
	//�ٵ� JSON����� ���� ġ�� �󸶳� ������ xhttp3.send(`{'name' : ${input_pizza_id.value}, �̷� ������ ������ ���� �� ���� }`);
	xhttp3.open('PUT', '/restful/sample/pizza/update');	


	//�̷��� �������� �㿡 �Ʒ��� stringify�޼���� ��ȯ���ָ� ��.  JSON.stringify�� JSON.parse�� �ݴ�. JSON.parse()�� JSON���� �� �� object�� ��ȯ���ִ� �ſ���  
	const pizza = {
		p_id: 		input_pizza_id.value, 
		p_name:		input_pizza_name.value,
		p_price:	input_pizza_price.value, 
		p_calories: input_pizza_calories.value
	}
	

	// xhr ��û��� ���� (JSON�������� �����ٰ� ������ �˷��� �Ѵ�.)
	
	xhttp3.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	
	//���� Content-Type: text/html;charset=utf-8 ���� Content-Type: application/json;charset=UTF-8�� �ٲ�
	
	
	// object -> JSON (stringify)
	
	
	xhttp3.send(JSON.stringify(pizza));
	
	
	//���� PizzaRestController���� Put�������ε� updatePizza() �� �޾ƺ��� 
	//�ٵ� ���⼭ �� �ǳ� �����ڵ���â���� ��Ʈ��ũ - ��pizza �������� Content-Type�� Content-Type: text/html;charset=utf-8��� �� ����
	//���� ���� xhr��û��� �������ֱ�	
	
});

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




//�ǽ�3. ajax�� insert(POST) ��û ������ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
const btn3 = document.getElementById('btn-xhttp-post');
const input_pizza_name2 = document.getElementById('post-pizza-name');
const input_pizza_price2 = document.getElementById('post-pizza-price');
const input_pizza_calories2 = document.getElementById('post-pizza-calories');

const out3 = document.getElementById('out3');

const xhttp4 = new XMLHttpRequest();
xhttp4.addEventListener('readystatechange', (e)=> {
	const readyState = e.target.readyState;
	
	if(readyState == 4) {
		
		// e.target.status : ��Ʈ�ѷ����� ����� ���� http ���� �ڵ尡 ��� �ִ�.
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

