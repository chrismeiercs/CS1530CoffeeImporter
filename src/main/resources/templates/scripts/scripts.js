/**
 * Created by George Totolos on 6/28/2015.
 */
$(document).ready(function() {
    $('.tab').hide();

    $('.tabs').find('a').on('click', function(e){
        e.preventDefault();
        $('.tabs').find('.current').removeClass('current');
        $(this).addClass('current');
        $(this.hash).show().siblings().hide();
    }).first().click();
});
