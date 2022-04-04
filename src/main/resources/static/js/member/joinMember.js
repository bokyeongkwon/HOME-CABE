'use strict';

       //유효성 체크 상태
        const validChkStatus = {
            id:false,
            email:false,
            nickname:false
        }

        //아이디 중복 확인
        const $id = document.getElementById('id');
        const $idDubChk = document.getElementById('idDubChk');

        $idDubChk.addEventListener('click', e=>{
            const xmlHttpreq = new XMLHttpRequest();

            const url = `/api/member/${$id.value}`;
            xmlHttpreq.open("GET", url);
            xmlHttpreq.send();

            xmlHttpreq.addEventListener('load', e=>{
                if(xmlHttpreq.status === 200){
                    console.log(xmlHttpreq.response);
                    const result = JSON.parse(xmlHttpreq.response);
                    console.log(result);
                    const $errmsg = $idDubChk.closest('div').querySelector('.errmsg');

                    if(result.rtcd === '00'){
                        $errmsg.textContent = '이미 사용 중인 아이디 입니다.';
                        $errmsg.style.display = 'block';
                    }else{
                        $errmsg.textContent = '';
                        $errmsg.style.display = 'none';
                        $idDubChk.textContent = '사용가능';
                        $idDubChk.disabled = 'disabled';
                        validChkStatus.id = true;
                    }
                }else{
                    console.log('Error', xmlHttpreq.status, xmlHttpreq.statusText);
                }
            });
        });

        //이메일 중복 확인
        const $email = document.getElementById('email');
        const $emailDubChk = document.getElementById('emailDubChk');

        $emailDubChk.addEventListener('click', e=>{
            const xmlHttpreq = new XMLHttpRequest();

            const url = `/api/member/${$id.value}`;
            xmlHttpreq.open("GET", url);
            xmlHttpreq.send();

            xmlHttpreq.addEventListener('load', e=>{
                if(xmlHttpreq.status === 200){
                    console.log(xmlHttpreq.response);
                    const result = JSON.parse(xmlHttpreq.response);
                    console.log(result);
                    const $errmsg = $emailDubChk.closest('div').querySelector('.errmsg');

                    if(result.rtcd === '00'){
                        $errmsg.textContent = '이미 사용 중인 이메일 입니다.';
                        $errmsg.style.display = 'block';
                    }else{
                        $errmsg.textContent = '';
                        $errmsg.style.display = 'none';
                        $emailDubChk.textContent = '사용가능';
                        $emailDubChk.disabled = 'disabled';
                        validChkStatus.email = true;
                    }
                }else{
                    console.log('Error', xmlHttpreq.status, xmlHttpreq.statusText);
                }
            });
        });

        //닉네임 중복 확인
        const $nickname = document.getElementById('nickname');
        const $nicknameDubChk = document.getElementById('nicknameDubChk');

        $nicknameDubChk.addEventListener('click', e=>{
            const xmlHttpreq = new XMLHttpRequest();

            const url = `/api/member/${$id.value}`;
            xmlHttpreq.open("GET", url);
            xmlHttpreq.send();

            xmlHttpreq.addEventListener('load', e=>{
                if(xmlHttpreq.status === 200){
                    console.log(xmlHttpreq.response);
                    const result = JSON.parse(xmlHttpreq.response);
                    console.log(result);
                    const $errmsg = $nicknameDubChk.closest('div').querySelector('.errmsg');

                    if(result.rtcd === '00'){
                        $errmsg.textContent = '이미 사용 중인 닉네임 입니다.';
                        $errmsg.style.display = 'block';
                    }else{
                        $errmsg.textContent = '';
                        $errmsg.style.display = 'none';
                        $nicknameDubChk.textContent = '사용가능';
                        $nicknameDubChk.disabled = 'disabled';
                        validChkStatus.nickname = true;
                    }
                }else{
                    console.log('Error', xmlHttpreq.status, xmlHttpreq.statusText);
                }
            });
        });

        //회원가입 버튼 클릭시
        joinBtn.addEventListener('click', e=>{

            //아이디 중복체크 미이행시
            if(!validChkStatus.id){
                 const $errmsg = $idDubChk.closest('div').querySelector('.errmsg');
                 $errmsg.textContent = '아이디 중복체크를 해주세요.';
                 $errmsg.style.display = 'none';
                 return;
            }

            //이메일 중복체크 미이행시
            if(!validChkStatus.email){
                 const $errmsg = $emailDubChk.closest('div').querySelector('.errmsg');
                 $errmsg.textContent = '이메일 중복체크를 해주세요.';
                 $errmsg.style.display = 'none';
                 return;
            }

            //별칭 중복체크 미이행시
            if(!validChkStatus.nickname){
                 const $errmsg = $nicknameDubChk.closest('div').querySelector('.errmsg');
                 $errmsg.textContent = '닉네임 중복체크를 해주세요';
                 $errmsg.style.display = 'none';
                 return;
            }
            e.target.closest('form').submit();
        });