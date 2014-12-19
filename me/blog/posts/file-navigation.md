File Navigation
===============

Recently I have been getting bothered by the way I navigate files in Vim.

Here is my current usage pattern:

1. open vim in project root
2. open [NERDTree](https://github.com/scrooloose/nerdtree) (`CTRL-N`)
3. navigate to file I wish to open (`j`|`k`)
4. open file (`o`|`s`)
5. (nerd tree closes automatically)
6. write some stuff
7. open more files via NERDTree for editing/reading
8. use alternate buffers to swap to previously used buffer on  window by window
   basis (`<Space><Tab>`)
9. open my previous buffers with [ctrlp](https://github.com/kien/ctrlp.vim)
   (`CTRL-F`)
10. navigate to previous buffer and open (`CTRL-J`|`CTRL-K`|`<CR>`)
11. realise that I have buffers open that I haven't used for 2 hours and it
    takes me 30 seconds to find the file I want to open.
12. open NERDTree again.
12. rage.
13. close vim
14. GOTO: step 1

It feels somewhat inefficient...


