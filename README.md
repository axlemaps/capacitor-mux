# capacitor-mux

Mux Native Uploader

## Install

```bash
npm install capacitor-mux
npx cap sync
```

## API

<docgen-index>

* [`uploadVideo(...)`](#uploadvideo)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### uploadVideo(...)

```typescript
uploadVideo(options: UploadVideo) => Promise<{ success: boolean; }>
```

| Param         | Type                                                |
| ------------- | --------------------------------------------------- |
| **`options`** | <code><a href="#uploadvideo">UploadVideo</a></code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### Interfaces


#### UploadVideo

| Prop            | Type                |
| --------------- | ------------------- |
| **`uploadUri`** | <code>string</code> |
| **`videoFile`** | <code>string</code> |

</docgen-api>
