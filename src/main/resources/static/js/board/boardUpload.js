'use strict';
//textArea => ck_editor 대체
ClassicEditor
		.create( document.querySelector( '#boardContent' ))
		.then( editor => {

			window.editor = editor;
		} )
		.catch( error => {
			console.error( error );
		} );

const $board = document.querySelector('.bbs-wrap');
const $category = ($board?.dataset.code)? $board.dataset.code : '';

//등록
const $upLoad = document.getElementById('upLoad');
$upLoad?.addEventListener("click", e=>{
  addForm.submit();
});
//목록
const $listBtn = document.getElementById('listBtn');
$listBtn?.addEventListener("click",e=>{
  const url = `/bbs/list?category=${category}`;
  location.href = url;
});
//분류자동 선택
const $options = document.querySelectorAll('#bcategory option');
[...$options].find(option=>option.value===category).setAttribute('selected','selected');