// 삭제 버튼이 클릭이 되면 removeForm submit 하기 

document.querySelector(".btn-danger").addEventListener("click", () => {
    document.querySelector("#removeForm").submit();
})