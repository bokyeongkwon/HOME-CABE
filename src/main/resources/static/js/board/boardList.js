'use strict';
//param 과 카테고리가 이 파일 안에서 정의되지 않았다; 타겟팅 필요.

  const $writeBtn = document.getElementById('writeBtn');
    $writeBtn?.addEventListener('click', e=>{
        location.href = `/board/add(cateNum=${param.cateNum})`;
    });

